import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String ip = "10.10.55.121";
        int port = 40000;

        Socket client = new Socket(ip, port);

        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

        Scanner sc = new Scanner(System.in);

        System.out.println("<< 서버 파일 목록 >>");
        int length = dataInputStream.readInt();
        for(int i = 0; i < length; i++) {
            System.out.println(dataInputStream.readUTF());
        }

        System.out.print("다운받을 파일의 이름을 입력하세요: ");
        String targetFileName = sc.nextLine();
        dataOutputStream.writeUTF(targetFileName);
        dataOutputStream.flush();

        int fileLength = dataInputStream.readInt();
        byte[] fileContents = new byte[fileLength];
        dataInputStream.readFully(fileContents);
        System.out.println(fileContents.length);

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\keduit\\Downloads\\" + targetFileName);
        fileOutputStream.write(fileContents);

        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
