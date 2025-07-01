import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // << 연락처 관리 시스템 >>
        // 1. 연락처 등록
        // 2. 연락처 목록
        // >>
        String ip = "10.10.55.131";
        int port = 24563;

        Socket client = new Socket(ip, port);

        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("<< 연락처 관리 시스템 >>");
            System.out.println("1. 연락처 등록");
            System.out.println("2. 연락처 목록");
            System.out.print(">> ");
            int selectedMenu = Integer.parseInt(sc.nextLine());
            dataOutputStream.writeInt(selectedMenu);
            dataOutputStream.flush();

            switch (selectedMenu) {
                case 1:
                    System.out.print("등록할 연락처: ");
                    dataOutputStream.writeUTF(sc.nextLine());
                    dataOutputStream.flush();
                    break;
                case 2:
                    System.out.println("연락처 받는중...");
                    List<String> contactList = new ArrayList<>();
                    while (true) {
                        String temp = dataInputStream.readUTF();

                        if (!temp.equals("end")) {
                            contactList.add(temp);
                        } else {
                            break;
                        }
                    }

                    if (!contactList.isEmpty()) {
                        System.out.println("------연락처 목록-------");
                        for (String contact : contactList) {
                            System.out.println((contactList.indexOf(contact) + 1) + ". " + contact);
                        }
                        System.out.println("----------------------");
                    } else {
                        System.out.println("전달받은 연락처가 없습니다.");
                    }
                    break;
                default:
                    System.out.println("메뉴에 없는 번호입니다.");
                    break;
            }
        }
    }
}
