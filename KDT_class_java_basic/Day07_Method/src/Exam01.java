import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Integer.parseInt(str);

        System.out.println(plus(10, 2, 3));
        System.out.println(plus(10, 2));
    }

    public static int plus(int num1, int num2){
        return num1 + num2;
    }

    public static int plus(int num1, int num2, int num3){
        return num1 + num2 + num3;
    }
}
