import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===동전 앞 뒤 맞추기===");

            System.out.print("숫자를 입력해주세요 (1.앞면/2.뒷면)(0.종료) : ");
            int selectedCoinFace = Integer.parseInt(sc.nextLine());

            if (selectedCoinFace == 0) {
                System.exit(0);
            } else if (selectedCoinFace == 1 || selectedCoinFace == 2) {
                int answer = (int) (Math.random() * 2 + 1);
                // 1-2사이의 난수
                if (answer == selectedCoinFace) {
                    System.out.println("맞췄습니다^^");
                } else {
                    System.out.println("땡! 틀렸습니다!");
                }
            } else {
                System.out.println("없는 번호입니다.\n다시 확인해주세요.");
            }

            System.out.println("------------------->restart");
        }
    }
}
