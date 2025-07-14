import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==============");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.println("==============");
        System.out.print("국어 : ");
        int korP = Integer.parseInt(sc.nextLine());
        System.out.print("영어 : ");
        int engP = Integer.parseInt(sc.nextLine());
        System.out.print("수학 : ");
        int mathP = Integer.parseInt(sc.nextLine());
        System.out.println("==============");
        int totalP = korP + engP + mathP;
        System.out.println("합계 : " + totalP);
        System.out.printf("평균 : %.2f\n", (totalP / 3.00f));
        System.out.println("==============");
    }
}
