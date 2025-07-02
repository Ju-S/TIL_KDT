import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // << 인증 시스템 >>
        // 1. 로그인
        // 2. 회원가입

        // 로그인을 선택할 시
        // id, password를 입력받아 회원여부 판단

        // 회원가입을 선택할 시,
        // id, password, name 3개의 값을 입력받아 가입
        // 회원가입 시점 password는 SHA256알고리즘으로 암호화
        String ip = "10.10.55.121";
        int port = 24563;

        Socket client = new Socket(ip, port);

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        String downloadPath = "C:\\Users\\keduit\\Downloads\\";

        Scanner sc = new Scanner(System.in);

        boolean isLogin = false;

        while (true) {
            if (!isLogin) {
                System.out.println("<< 인증 시스템 >>");
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("0. 시스템 종료");
                System.out.print(">> ");
                int selectedMenu = Integer.parseInt(sc.nextLine());
                dataOutputStream.write(selectedMenu);
                dataOutputStream.flush();

                switch (selectedMenu) {
                    case 1: {
                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("PW: ");
                        String password = sc.nextLine();

                        dataOutputStream.writeUTF(id);
                        dataOutputStream.flush();
                        dataOutputStream.writeUTF(password);
                        dataOutputStream.flush();

                        isLogin = dataInputStream.readBoolean();

                        if (isLogin) {
                            System.out.println("로그인 성공.");
                        } else {
                            System.out.println("로그인 실패.");
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("PW: ");
                        String password = sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        dataOutputStream.writeUTF(id);
                        dataOutputStream.flush();
                        dataOutputStream.writeUTF(password);
                        dataOutputStream.flush();
                        dataOutputStream.writeUTF(name);
                        dataOutputStream.flush();
                        break;
                    }
                    case 0:
                        client.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("없는 메뉴입니다.");
                        break;
                }
            } else {
                System.out.println("<< 파일 다운로드 시스템 >>");
                System.out.println("1. 파일 목록");
                System.out.println("2. 파일 다운로드");
                System.out.println("0. 로그아웃");
                System.out.print(">> ");
                int selectedMenu = Integer.parseInt(sc.nextLine());
                dataOutputStream.write(selectedMenu);
                dataOutputStream.flush();

                switch (selectedMenu) {
                    case 1: {
                        System.out.println("======파일목록=====");
                        int fileLength = dataInputStream.readInt();
                        for(int i = 0; i < fileLength; i++) {
                            System.out.println(dataInputStream.readUTF());
                        }
                        System.out.println("==================");
                        break;
                    }
                    case 2: {
                        System.out.println("======파일목록=====");
                        int fileLength = dataInputStream.readInt();
                        for(int i = 0; i < fileLength; i++) {
                            System.out.println(dataInputStream.readUTF());
                        }
                        System.out.println("==================");
                        System.out.print("다운받을 파일명을 입력하세요: ");
                        String targetFileName = sc.nextLine();
                        dataOutputStream.writeUTF(targetFileName);
                        dataOutputStream.flush();

                        int fileSize = dataInputStream.readInt();
                        byte[] fileContents = new byte[fileSize];

                        dataInputStream.readFully(fileContents);
                        System.out.println(downloadPath + targetFileName);
                        FileOutputStream fileOutputStream = new FileOutputStream(downloadPath + targetFileName);
                        fileOutputStream.write(fileContents);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        System.out.println("======다운완료======");
                        break;
                    }
                    case 0:
                        isLogin = false;
                        break;
                    default:
                        System.out.println("없는 메뉴입니다.");
                        break;
                }
            }
        }
    }
}
