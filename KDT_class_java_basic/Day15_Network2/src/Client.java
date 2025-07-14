import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String ip = "10.10.55.121";
        int port = 24563;

        Socket client = new Socket(ip, port);

        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

        while (true) {
            System.out.println("<< 이용 할 서비스를 선택하세요. >>");
            System.out.println("1. 현재 날짜 및 시간");
            System.out.println("2. 로또 번호 뽑기");
            System.out.println("3. 사칙 연산 계산");
            // 연산자, 정수, 정수 순으로 입력 받아 결과를 출력
            System.out.print(">> ");

            int selectedMenu = Integer.parseInt(sc.nextLine());

            switch (selectedMenu) {
                case 1:
                    dataOutputStream.write(selectedMenu);
                    System.out.println("서버의 응답을 기다리는 중 입니다...");
                    String currentDate = dataInputStream.readUTF();
                    System.out.println("현재 날짜 및 시간은: " + currentDate + " 입니다.");
                    break;
                case 2:
                    dataOutputStream.write(selectedMenu);
                    System.out.println("서버의 응답을 기다리는 중 입니다...");
                    String lotto = dataInputStream.readUTF();
                    System.out.println("생성된 로또 번호는: " + lotto + " 입니다.");
                    break;
                case 3:
                    dataOutputStream.write(selectedMenu);
                    System.out.print("연산자를 입력하세요(+, -, *, /): ");
                    String operator = sc.nextLine();
                    dataOutputStream.writeUTF(operator);
                    System.out.print("첫 번째 숫자: ");
                    int num1 = Integer.parseInt(sc.nextLine());
                    dataOutputStream.write(num1);
                    System.out.print("두 번째 숫자: ");
                    int num2 = Integer.parseInt(sc.nextLine());
                    dataOutputStream.write(num2);
                    System.out.println(num1 + " " + operator + " " + num2 + " = " + dataInputStream.read());
                    break;
                default:
                    System.out.println("없는 메뉴입니다.");
                    break;
            }
        }
    }
}
