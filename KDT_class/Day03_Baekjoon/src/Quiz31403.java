import java.util.Scanner;

public class Quiz31403 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        System.out.println(num1+num2-num3);
        String combineNum = Integer.toString(num1) + Integer.toString(num2);
        System.out.println(Integer.parseInt(combineNum)-num3);
    }
}
