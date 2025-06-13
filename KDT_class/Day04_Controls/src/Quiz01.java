import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("===구구단 출력 프로그램===");

            System.out.print("1-9단 중 선택 : ");
            int num = Integer.parseInt(sc.nextLine());

            if (0 < num && num < 10) {
                int i = 0;
                while (i++ < 9) {
                    System.out.println(num + " * " + i + " = " + (num * i));
                }
            } else {
                System.out.println("입력된 수가 1-9범위를 벗어났습니다.");
            }
        }
    }
}
