import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
        //문자열이 아닌 숫자 입력 받아보기
        Scanner sc = new Scanner(System.in);

        System.out.print("숫자를 입력하세요 : ");
        int inputNum = Integer.parseInt(sc.nextLine());

        System.out.println("입력하신 숫자와 10을 더한 결과는 " + (inputNum + 10) + "입니다.");
    }
}
