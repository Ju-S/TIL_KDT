import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Hello를 출력할 횟수 입력 : ");
        int cnt = Integer.parseInt(sc.nextLine());
        int i = 0;

        while (cnt > i++) {
            System.out.println(i + " : Hello");
        }
    }
}
