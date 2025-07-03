package client;

import client.classes.LoginInfo;
import client.view.AuthSystemView;
import client.view.PostSystemView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String ip = "10.5.5.1";
        int port = 24567;

        LoginInfo currentLoginInfo = new LoginInfo("", "", false);

        Socket client = new Socket(ip, port);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        Scanner sc = new Scanner(System.in);

        AuthSystemView authSystemView = new AuthSystemView();
        PostSystemView postSystemView = new PostSystemView();

        while(true) {
            // 로그인 상태 보내기
            dos.writeBoolean(currentLoginInfo.isLoginStatus());
            dos.flush();

            if(!currentLoginInfo.isLoginStatus()) {  // 로그인 안되어있다면 인증 시스템
                currentLoginInfo = authSystemView.authSystem(sc, dis, dos);
            } else {  // 로그인 되어있다면 방명록 시스템
                currentLoginInfo = postSystemView.postSystem(sc, dis, dos, currentLoginInfo);
            }
        }
    }
}
