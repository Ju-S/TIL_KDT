import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        // 시험 점수를 입력하세요(0-100) :
        Scanner sc = new Scanner(System.in);

        System.out.print("시험 점수를 입력하세요(0-100) : ");
        int examScore = Integer.parseInt(sc.nextLine());

        if (0 <= examScore && examScore <= 100) {
            if (examScore >= 90) {
                System.out.println("당신의 점수 등급은 A입니다.");
            } else if (examScore >= 80) {
                System.out.println("당신의 점수 등급은 B입니다.");
            } else if (examScore >= 70) {
                System.out.println("당신의 점수 등급은 C입니다.");
            } else {
                System.out.println("당신의 점수 등급은 D입니다.");
            }
        } else {
            System.out.println("점수가 입력 범위를 벗어났습니다.(0-100)");
        }
    }
}
