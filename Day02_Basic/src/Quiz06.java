import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        // 노래 제목을 입력하세요.
        // 가수를 입력하세요.

        //당신이 입력한 노래는 [제목] 이며, 가수 [가수]씨가 부른 노래입니다.

        Scanner sc = new Scanner(System.in);
        System.out.print("노래 제목을 입력하세요 : ");
        String title = sc.nextLine();
        System.out.print("가수를 입력하세요 : ");
        String singer = sc.nextLine();
        System.out.println("당신이 입력한 노래는 " + title + "이며, 가수 " + singer + "씨가 부른 노래입니다.");
    }
}
