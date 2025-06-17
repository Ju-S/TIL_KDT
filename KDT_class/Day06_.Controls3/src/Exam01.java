import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        while(true) {
            try {
                System.out.print("숫자를 입력하세요 : ");
                num = Integer.parseInt((sc.nextLine()));

                break;
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
        System.out.println("입력된 숫자는 " + num + "입니다.");
        System.out.println("프로그램 종료");
    }
}
