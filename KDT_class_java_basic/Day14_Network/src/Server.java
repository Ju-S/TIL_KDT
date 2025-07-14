import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 19283;

        ServerSocket server = new ServerSocket(port);
        System.out.println("클라이언트 연결을 기다리는 중 입니다..");
        Socket client = server.accept();

        System.out.println(client.getInetAddress() + " 에서 접속했습니다.");

        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        while(true) {
            String str = JOptionPane.showInputDialog("보낼 메세지를 입력하세요: ");
            System.out.println("내가 보낸 메세지: " + str);
            outputStream.writeUTF(str);
            outputStream.flush();

            System.out.println("받은 메세지: " + inputStream.readUTF());
        }
    }
}
