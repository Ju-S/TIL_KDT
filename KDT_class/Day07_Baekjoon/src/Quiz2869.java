import java.util.Scanner;

public class Quiz2869 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int A = Integer.parseInt(str.split(" ")[0]);
        int B = Integer.parseInt(str.split(" ")[1]);
        int V = Integer.parseInt(str.split(" ")[2]);

        System.out.println((int)(Math.ceil((double)(V - A) / (A - B)) + 1));
    }
}
