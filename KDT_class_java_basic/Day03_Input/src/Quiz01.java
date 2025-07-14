import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        // 두 숫자를 입력받아
        // 덧셈한 결과를 출력하는 프로그램

        Scanner sc = new Scanner(System.in);

        System.out.print("덧셈을 할 두 숫자를 입력하세요 : ");
        String str = sc.nextLine();
        int num1 = Integer.parseInt(str.split(" ")[0]);
        int num2 = Integer.parseInt(str.split(" ")[1]);

        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
    }
}
