import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

class Multie2 extends Thread {

    private DataOutputStream dos;

    public Multie2 (){}
    public Multie2 (DataOutputStream dos) {
        this.dos=dos;
    }

    public void run() {
        try {
            while(true) {
                // 채팅 입력하는 ui가 계속 반복
                String msg = JOptionPane.showInputDialog("클라이언트에게 보낼 메세지 입력 : ");
                dos.writeUTF(msg);
                dos.flush();
                System.out.println("내가 보낸 메세지 : " + msg);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

public class Client {
    public static void main(String[] args) throws Exception {
        String ip = "10.10.55.121";
        //ip = "10.5.5.0";

        int port = 24563;
        //port = 40000;

        System.out.println("서버에 접속중..");
        Socket client = new Socket(ip, port);
        System.out.println("서버에 접속성공");

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        new Multie2(dos).start();

        while(true) {
            String serverMsg = dis.readUTF();
            System.out.println("서버가 보낸 메세지 : " + serverMsg);
        }
    }
}
