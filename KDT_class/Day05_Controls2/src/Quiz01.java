import java.util.Scanner;

public class Quiz01 {
    public static int getValidNum(String print) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(print);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String operator;
        // 사용자 입력 연산자 변수
        int num1, num2;
        // 연산이 될 정수 변수

        // int result = 0;
        // 연산 결과값 저장용

        while (true) {
            System.out.println("===계산기 프로그램===\n");

            System.out.print("연산자 입력(+,-,*,/)(q:종료) : ");
            operator = sc.nextLine();
            // 사용자 입력 연산자

            if (operator.equals("q")) {
                System.exit(0);
                // 사용자 입력이 q인 경우, 프로그램 종료
            } else if (!operator.equals("+")
                    && !operator.equals("-")
                    && !operator.equals("*")
                    && !operator.equals("/")) {
                System.out.println("없는 연산자 입니다.\n");
                // 없는 연산자의 경우, 수를 받는 과정을 생략한다
            } else {
                // 사용자 입력이 +, -, *, / 인 경우에만 연산할 수를 입력할 수 있도록 조건
                num1 = getValidNum("첫 번째 숫자 입력 : ");
                num2 = getValidNum("두 번째 숫자 입력 : ");

                System.out.println("\n======= 결과 =======\n");

                System.out.print(num1 + operator + num2 + "=");
                switch (operator) {
                    case "+":
                        System.out.println(num1 + num2);
                        break;
                    case "-":
                        System.out.println(num1 - num2);
                        break;
                    case "*":
                        System.out.println(num1 * num2);
                        break;
                    case "/":
                        System.out.println((double) num1 / num2);
                        break;
                }
                System.out.println();

                // System.out.println(num1 + operator + num2 + "=" + result + "\n");
                // int형으로 선언된 result하나로는 소수점까지 표현이 어렵기 때문에 switch-case문 안에서 출력으로 변경
            }
        }
    }
}
