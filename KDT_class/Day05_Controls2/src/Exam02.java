import java.util.Scanner;

public class Exam02 {
    public static void main(String[] args) {
        // switch - 선택 분기문

        Scanner sc = new Scanner(System.in);

        System.out.print("1, 2, 3 중에 입력 : ");
        int input = Integer.parseInt(sc.nextLine());

        switch (input) {
            case 1:
                System.out.println("1번입니다.");
                break;
            case 2:
                System.out.println("2번입니다.");
                break;
            case 3:
                System.out.println("3번입니다.");
                break;
            default:
                System.out.println("없는 메뉴입니다.\n다시확인해주세요.");
                break;
        }
    }
}
