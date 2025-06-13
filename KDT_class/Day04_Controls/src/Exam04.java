import java.util.Scanner;

public class Exam04 {
    public static void main(String[] args) {
        // 변수의 종류
        // 지역변수 / 매개변수 / 멤버변수

        Scanner sc = new Scanner(System.in);

        int balance = 3000;
        int colaPrice = 1000, ciderPrice = 800, maesilPrice = 1500;
        int colaHas = 0, ciderHas = 0, maesilHas = 0;

        while (true) {
            System.out.println("<< 자판기 시뮬레이터 >>");
            System.out.println("1. 콜라(1000) / 2. 사이다(800) / 3. 매실차(1500) / 0. 소지품 / 9. 종료");
            System.out.print(">> ");
            int selectedMenu = Integer.parseInt(sc.nextLine());

            if (selectedMenu == 1) {
                if(balance < colaPrice) {
                    System.out.println("소지금이 부족합니다.");
                } else {
                    balance -= colaPrice;
                    colaHas++;
                    System.out.println("콜라를 구매합니다.\n콜라 +1개");
                }
            } else if (selectedMenu == 2) {
                if(balance < ciderPrice) {
                    System.out.println("소지금이 부족합니다.");
                } else {
                    balance -= ciderPrice;
                    ciderHas++;
                    System.out.println("사이다를 구매합니다.\n사이다 +1개");
                }
            } else if (selectedMenu == 3) {
                if(balance < maesilPrice) {
                    System.out.println("소지금이 부족합니다.");
                } else {
                    balance -= maesilPrice;
                    maesilHas++;
                    System.out.println("매실차를 구매합니다.\n매실차 +1개");
                }
            } else if (selectedMenu == 0) {
                System.out.println("=====소지품=====");
                System.out.println("소지금 : " + balance + "원");
                System.out.println("콜라   : " + colaHas + "개");
                System.out.println("사이다 : " + ciderHas + "개");
                System.out.println("매실차 : " + maesilHas + "개");
                System.out.println("===============");
            } else if (selectedMenu == 9) {
                break;
            }
        }
    }
}
