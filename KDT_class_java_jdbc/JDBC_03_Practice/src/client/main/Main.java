package client.main;

import client.session.LoginInfo;
import client.view.AuthView;
import client.view.PostView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            String ip = "10.5.5.1";
            int port = 24567;

            LoginInfo memberSession = new LoginInfo("", "", false);

            Socket client = new Socket(ip, port);

            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            while (true) {
                dos.writeBoolean(memberSession.isLoginStatus());
                dos.flush();

                if (memberSession.isLoginStatus()) {
                    memberSession = new PostView().postSystem(dis, dos, memberSession);
                } else {
                    memberSession = new AuthView().authSystem(dis, dos);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
