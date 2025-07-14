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
        ip = "10.10.55.121";
        int port = 24563;

        Socket client = new Socket(ip, port);

        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("<< 연락처 관리 시스템 >>");
            System.out.println("1. 연락처 등록");
            System.out.println("2. 연락처 목록");
            System.out.println("3. 연락처 검색");
            System.out.println("4. 연락처 수정");
            System.out.println("5. 연락처 삭제");
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
                    printClient(dataInputStream, "등록된 연락처가 없습니다.");
                    break;
                case 3:
                    System.out.print("검색할 번호를 입력하세요: ");
                    String targetPhno = sc.nextLine();
                    dataOutputStream.writeUTF(targetPhno);
                    dataOutputStream.flush();

                    printClient(dataInputStream, "검색된 연락처가 없습니다.");
                    break;
                case 4:
                    System.out.println("수정할 ID를 입력하세요: ");
                    dataOutputStream.writeInt(Integer.parseInt(sc.nextLine()));

                    if(!dataInputStream.readBoolean()){
                        System.out.print("수정할 연락처를 입력하세요: ");
                        dataOutputStream.writeUTF(sc.nextLine());
                    } else {
                        System.out.println("검색된 연락처가 없습니다.");
                    }
                    break;
                case 5:
                    System.out.print("삭제할 번호를 입력하세요: ");
                    int targetId = Integer.parseInt(sc.nextLine());
                    dataOutputStream.writeInt(targetId);
                    dataOutputStream.flush();
                    break;
                default:
                    System.out.println("메뉴에 없는 번호입니다.");
                    break;
            }
        }
    }

    public static void printClient(DataInputStream dataInputStream, String errorMsg) throws Exception{
        int resultSize = dataInputStream.readInt();

        if (resultSize > 0) {
            System.out.println("------연락처 목록-------");
            for (int i = 0; i < resultSize; i++) {
                String phno = dataInputStream.readUTF();
                int id = dataInputStream.readInt();
                System.out.println(id + ". " + phno);
            }
            System.out.println("----------------------");
        } else {
            System.out.println(errorMsg);
        }
    }
}
