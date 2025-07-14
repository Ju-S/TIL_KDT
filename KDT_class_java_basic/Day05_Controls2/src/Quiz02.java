import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        // double -> int 형변환 시, 소수점 아래 버림

        System.out.print("0-9 까지의 랜덤 수 : ");
        System.out.println((int)(Math.random() * 10));

        System.out.print("1-10 까지의 랜덤 수 : ");
        System.out.println((int)(Math.random() * 10) + 1);

        System.out.print("20-35 까지의 랜덤 수 : ");
        System.out.println((int)(Math.random() * 16) + 20);

        System.out.print("0 또는 1 : ");
        System.out.println((int)(Math.random() * 2));

        System.out.println((int)1.99); // 결과 값으로 1이 나온다
    }
}

