import java.util.Scanner;

public class Quiz2420 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        long n = Long.parseLong(str.split(" ")[0]);
        long m = Long.parseLong(str.split(" ")[1]);

        System.out.println(Math.abs(n-m));
    }
}
