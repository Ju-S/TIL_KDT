import java.util.Scanner;

public class Exam02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("당신이 좋아하는 과일을 선택하세요.");
        System.out.println("-----------------------------");
        System.out.println("1. 사과");
        System.out.println("2. 딸기");
        System.out.println("3. 수박");
        System.out.println("-----------------------------");
        System.out.print("선택: ");
        int selectedFruit = Integer.parseInt(sc.nextLine());


        if(selectedFruit == 1) {
            System.out.println("사과는 영어로 apple입니다.");
        } else if(selectedFruit == 2) {
            System.out.println("딸기는 영어로 strawberry입니다.");
        } else if(selectedFruit == 3) {
            System.out.println("수박은 영어로 watermelon입니다.");
        } else {
            System.out.println("메뉴를 다시 확인해주세요.");
        }
    }
}
