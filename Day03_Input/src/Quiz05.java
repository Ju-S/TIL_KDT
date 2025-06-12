import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("1-100 사이 입력: ");
        int num = Integer.parseInt(sc.nextLine());

        System.out.println("===결과===");

        if (1 <= num && num <= 100) {
            if (num % 2 == 0) {
                System.out.println("입력한 수는 짝수 입니다.");
            } else {
                System.out.println("입력한 수는 홀수 입니다.");
            }
        } else {
            System.out.println("입력된 수가 1-100범위를 벗어났습니다.");
        }
    }
}
