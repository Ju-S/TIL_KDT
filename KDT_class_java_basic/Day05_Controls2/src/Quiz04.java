import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("===가위 바위 보 게임===");

            System.out.print("숫자를 선택하세요(1.가위/2.바위/3.보)(0.종료): ");
            int player = Integer.parseInt(sc.nextLine());  // 플레이어의 수를 입력받는다
            int computer = (int)(Math.random() * 3 + 1);  // 컴퓨터가 낼 수를 난수로 표현 1-3사이의 수

            if(player == 0) {  // 0 선택 시, 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            } else if(player != 1 && player != 2 && player != 3){  // 0-3이 아닌 번호를 입력하였을 시, 번호 다시 선택하도록
                System.out.println("없는 번호입니다.\n다시선택하세요.");
                continue;
            }

            System.out.println("=========결과=========");
            System.out.print("당신은 ");
            switch(player) {  // 플레이어(player)가 입력한 정보를 출력
                case 1:
                    System.out.print("가위");
                    break;
                case 2:
                    System.out.print("바위");
                    break;
                case 3:
                    System.out.print("보");
                    break;
            }
            System.out.println("를 냈습니다.");

            System.out.print("컴퓨터는 ");
            switch(computer) {  // 난수(computer)를 대입하여 정보를 출력
                case 1:
                    System.out.print("가위");
                    break;
                case 2:
                    System.out.print("바위");
                    break;
                case 3:
                    System.out.print("보");
                    break;
            }
            System.out.println("를 냈습니다.");

            System.out.println("====================");
            // 플레이어가 이기는 경우(1:가위, 2:바위, 3:보)
            if((player == 1 && computer == 3) ||
                    (player == 2 && computer == 1) ||
                    (player == 3 && computer == 2)){
                System.out.println("플레이어가 이겼습니다.");
            } else if (player == computer) {  // 서로 같은 수를 내어 비기는 경우
                System.out.println("비겼습니다.");
            } else {  // 플레이어가 지는 경우
                System.out.println("플레이어가 졌습니다.");
            }
        }
    }
}
