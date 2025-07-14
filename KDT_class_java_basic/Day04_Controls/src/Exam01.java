import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) {
            if(i % 5 == 0 && i != 0) {
                System.out.print("\n");
            }
            System.out.print((i + 1) + " ");
        }
    }
}
