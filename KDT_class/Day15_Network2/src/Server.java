import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 19283;
        ServerSocket server = new ServerSocket(port);
        Socket client = server.accept();

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        while(true) {
            int selectedMenu = dataInputStream.read();

            switch(selectedMenu) {
                case 1:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일, hh:mm:ss");
                    String currentTime = sdf.format(System.currentTimeMillis());
                    dataOutputStream.writeUTF(currentTime);
                    break;
                case 2:
                    List<Integer> lottos = new ArrayList<>();
                    for(int i = 0; i < 7; i++) {
                        boolean duplicationFlag = false;
                        int randLotto = (int)(Math.random() * 45) + 1;
                        for(int lotto : lottos) {
                            if (randLotto == lotto) {
                                i--;
                                duplicationFlag = true;
                            }
                        }
                        if(!duplicationFlag) {
                            lottos.add(randLotto);
                        }
                    }

                    String lottoStr = "";
                    for(int lotto : lottos) {
                        lottoStr += lotto + " ";
                    }
                    dataOutputStream.writeUTF(lottoStr);
                    break;
                case 3:
                    String operator = dataInputStream.readUTF();
                    int num1 = dataInputStream.readInt();
                    System.out.println(num1);
                    int num2 = dataInputStream.readInt();
                    System.out.println(num2);
                    dataOutputStream.writeUTF(calc(operator, num1, num2));
                    dataOutputStream.flush();
                    break;
                default:
                    dataOutputStream.writeUTF("잘못된 메뉴를 요청함.");
                    break;
            }
            dataOutputStream.flush();
        }
    }

    public static String calc(String operator, int num1, int num2) {
        switch(operator) {
            case "+":
                return (num1 + num2) + "";
            case "-":
                return (num1 - num2) + "";
            case "*":
                return (num1 * num2) + "";
            case "/":
                return ((double)num1 / num2) + "";
            default:
                return "지원하지 않는 연산자입니다.";
        }
    }
}
