package server.main;

import server.dao.MemberDAO;
import server.dao.PostDAO;
import server.thread.ServerThread;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            int port = 24567;
            ServerSocket server = new ServerSocket(port);

            MemberDAO memberDAO = new MemberDAO();
            PostDAO postDAO = new PostDAO();

            while (true) {
                Socket client = server.accept();
                new ServerThread(client, memberDAO, postDAO).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
