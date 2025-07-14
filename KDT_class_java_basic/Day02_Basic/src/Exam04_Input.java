// Scanner를 사용하여 사용자로부터 입력을 받는 예제
// 콘솔을 통해 문자열을 입력받고 출력하는 기본적인 입출력을 보여줍니다

import java.util.Scanner;

public class Exam04_Input {
    public static void main(String[] args) { // ctrl + shift + 'o' - auto import(eclipse)
        // Scanner 객체를 생성하여 키보드로부터 입력을 받을 준비를 합니다
        Scanner sc = new Scanner(System.in); // 문자열을 입력받는 도구

        System.out.print("당신의 이름을 입력하세요 : ");

        // nextLine() 메소드를 사용하여 한 줄의 문자열을 입력받아 name 변수에 저장합니다
        String name = sc.nextLine(); // 사용자로부터 문자열을 입력받는 기능

        // 입력받은 문자열을 콘솔에 출력합니다
        System.out.println(name + "님 안녕하세요.");

        System.out.print(name + "님의 연락처를 입력해주세요 : ");
        String phoneNo = sc.nextLine();
        System.out.print(name + "님 자리가 생기면 " + phoneNo + "로 연락 드리겠습니다.");
    }
}
