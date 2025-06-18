import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int students = 3;

        String[] name = new String[students];
        int[] kor = new int[students];
        int[] eng = new int[students];

        for (int i = 0; i < students; i++) {
            System.out.print((i + 1) + "번째 학생 이름 : ");
            name[i] = sc.nextLine();
            System.out.print(name[i] + "학생 국어 : ");
            kor[i] = Integer.parseInt(sc.nextLine());
            System.out.print(name[i] + "학생 영어 : ");
            eng[i] = Integer.parseInt(sc.nextLine());
        }

        System.out.println("이름  국어  영어  합계  평균");
        for (int i = 0; i < students; i++) {
            System.out.print(name[i] + "  ");
            System.out.print(kor[i] + "   ");
            System.out.print(eng[i] + "   ");
            System.out.print((kor[i] + eng[i]) + "  ");
            System.out.println((kor[i] + eng[i]) / 2.0);
        }
    }
}
