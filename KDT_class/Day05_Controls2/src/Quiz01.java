import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String operator;
        // 사용자 입력 연산자 변수
        int num1, num2;
        // 연산이 될 정수 변수

        int result = 0;
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
                System.out.print("첫 번째 숫자 입력 : ");
                num1 = Integer.parseInt(sc.nextLine());
                System.out.print("두 번째 숫자 입력 : ");
                num2 = Integer.parseInt(sc.nextLine());

                System.out.println("\n======= 결과 =======\n");

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        break;
                }

                System.out.println(num1 + operator + num2 + "=" + result + "\n");
            }
        }
    }
}
