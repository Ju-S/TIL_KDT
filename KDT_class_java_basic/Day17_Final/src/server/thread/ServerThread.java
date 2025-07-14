package server.thread;

import server.dao.PostDAO;
import server.dao.UserDAO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    UserDAO userDAO;
    PostDAO postDAO;
    Socket client;

    DataInputStream dis;
    DataOutputStream dos;

    PostWorker postWorker;
    AuthWorker authWorker;

    public ServerThread() {}
    public ServerThread(Socket client, UserDAO userDAO, PostDAO postDAO) {
        try {
            this.userDAO = userDAO;
            this.postDAO = postDAO;
            this.client = client;

            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());

            postWorker = new PostWorker(dis, dos, postDAO);
            authWorker = new AuthWorker(dis, dos, userDAO);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(client.getInetAddress() + " : 접속해제");
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                // client 에서 보내는 현재 로그인 상태
                boolean userLoginStatus = dis.readBoolean();
                System.out.println("로그인 상태: " + userLoginStatus);

                if(userLoginStatus) {
                    postWorker.processPost();
                } else {
                    authWorker.processAuth();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(client.getInetAddress() + " : 접속해제");
        }
    }
}
