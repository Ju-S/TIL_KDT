public class Exam02 {
    public static void main(String[] args) {
        System.out.println(divide(36334, 21262));

        System.out.println(bigger(5, 10));

        System.out.println(isEven(50));
    }

    public static double divide(int num1, int num2) {
        return (double) num1 / num2;
    }

    public static int bigger(int num1, int num2) {
        if (num1 > num2) {
            return num1;
        } else if (num1 < num2) {
            return num2;
        } else {
            return 0;
        }
    }

    public static boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
