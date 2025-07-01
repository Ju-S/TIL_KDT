import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws Exception {
        int port = 19283;
        port = 24563;

        String ip = "10.10.55.131";
        ip = "10.10.55.121";

        Socket client = new Socket(ip, port);

        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        while(true) {
//            String str = JOptionPane.showInputDialog("보낼 메세지를 입력하세요: ");
//
//            outputStream.writeUTF(str);
//            outputStream.flush();
            System.out.println(inputStream.readUTF());
        }

        //System.out.println(inputStream.readUTF());
    }
}
