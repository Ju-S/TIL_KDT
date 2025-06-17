import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 0;  // 잔액 저장용

        while(true) {
            System.out.println("1. 게임 시작");
            System.out.println("2. 잔액 충전");
            System.out.println("3. 잔액 조회");
            System.out.println("4. 게임 종료");
            System.out.print("번호를 선택하세요 : ");
            int selectedMenu = Integer.parseInt(sc.nextLine());

            switch(selectedMenu){
                case 1:
                    int selectedHorse;  // 선택한 말 번호
                    int bet;  // 배팅금액

                    System.out.println("말들이 준비 되었습니다. 배팅하고 싶은 말을 선택해주세요.");
                    System.out.println("1번 말 : 우승한 경력이 많지만 은퇴시기를 앞둔 늙은 말");
                    System.out.println("2번 말 : 3월달 연습 기록이 가장 좋았던 젊은 말");
                    System.out.println("3번 말 : 떠오르는 신예 말");

                    System.out.print("번호를 입력하세요 : ");
                    selectedHorse = Integer.parseInt(sc.nextLine());  // 말 선택
                    System.out.println(selectedHorse + "번 말을 고르셨습니다.");
                    System.out.println("선택한 말이 우승할 경우 배팅액의 50퍼센트를 받게 됩니다.");

                    System.out.println("현재 잔액은 " + balance + "원 입니다.");
                    System.out.print("배팅할 금액을 입력해주세요(최소 금액: 1000원): ");
                    bet = Integer.parseInt(sc.nextLine());  // 배팅금액 입력

                    if(bet < 1000){
                        System.out.println("최소 금액이 충족되지 않습니다.");
                    } else if (bet > balance) {
                        System.out.println("잔액보다 많은 금액이 입력되었습니다.");
                    } else {
                        int winnerHorse = (int) (Math.random() * 3) + 1;  // 출전하는 말의 마리 수
                        if(winnerHorse == selectedHorse) {  // 우승 말을 맞춘 경우
                            System.out.println("축하합니다! " + winnerHorse + "번 말이 우승했습니다!");
                            balance += bet / 2;  // 배팅액의 50% 충전
                            System.out.println("현재 잔액은 : " + balance + "원 입니다.");
                        } else {
                            System.out.println("아쉽습니다. " + winnerHorse + "번 말이 우승했습니다.");
                            balance -= bet;  // 배팅액 회수
                            System.out.println("현재 잔액은 : " + balance + "원 입니다.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("얼마를 충전하시겠습니까? (단위: 원)");
                    System.out.print(">> ");
                    balance += Integer.parseInt(sc.nextLine());
                    System.out.println("충전 후 잔액은 : " + balance + "원 입니다.");
                    break;
                case 3:
                    System.out.println("현재 잔액은 : " + balance + "원 입니다.");
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("없는 번호입니다. 다시입력해주세요.");
                    break;
            }
        }
    }
}
