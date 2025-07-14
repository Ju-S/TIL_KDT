import java.util.Scanner;

public class Exam03 {
    public static void main(String[] args) {
        // 숫자 데이터에 대해서는 비교식에 == 을 사용한다.
        // 문자열 데이터에 대해서는 비교식에 .equals 를 사용한다.

        Scanner sc = new Scanner(System.in);

        System.out.print("단어를 입력하세요 : ");
        String word = sc.nextLine();

        if(word.equals("Apple")){ // word == "Apple" 시, word에는 주소값이 들어가있기 때문에 Apple을 입력해도 false 결과가 나온다.
            System.out.println("사과 입니다.");
        } else {
            System.out.println("사전에 없습니다.");
        }
    }
}
