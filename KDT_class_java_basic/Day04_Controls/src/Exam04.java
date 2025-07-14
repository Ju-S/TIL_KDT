import java.util.Scanner;

public class Exam04 {
    public static void main(String[] args) {
        // 변수의 종류
        // 지역변수 / 매개변수 / 멤버변수

        Scanner sc = new Scanner(System.in);

        int balance = 3000;
        // 소지금
        int colaPrice = 1000, ciderPrice = 800, maesilPrice = 1500;
        // 각 메뉴의 가격
        int colaHas = 0, ciderHas = 0, maesilHas = 0;
        // 각 메뉴의 소지하고 있는 갯수

        while (true) {
            System.out.println("<< 자판기 시뮬레이터 >>");
            System.out.println("1. 콜라(" + colaPrice + ") / 2. 사이다(" + ciderPrice + ") / 3. 매실차(" + maesilPrice + ") / 0. 소지품 / 9. 종료");
            System.out.print(">> ");
            int selectedMenu = Integer.parseInt(sc.nextLine());
            // 메뉴 번호 사용자 입력

            if (selectedMenu == 1) {
                // 입력된 번호가 콜라(1) 일 때,
                if (balance < colaPrice) {
                    // 소지금(balance)이 콜라의 가격(colaPrice)보다 부족한 경우
                    System.out.println("소지금이 부족합니다.");
                } else {
                    // 소지금(balance)이 충분한 경우
                    balance -= colaPrice;
                    // 소지금(balance)에서 콜라의 가격(colaPrice)만큼 감소
                    colaHas++;
                    // 소지품의 콜라(colaHas)를 증가
                    System.out.println("콜라를 구매합니다.\n콜라 +1개");
                }
            } else if (selectedMenu == 2) {
                // 입력된 번호가 사이다(2) 일 때,
                if (balance < ciderPrice) {
                    // 소지금(balance)이 사이다의 가격(ciderPrice)보다 부족한 경우
                    System.out.println("소지금이 부족합니다.");
                } else {
                    // 소지금(balance)이 충분한 경우
                    balance -= ciderPrice;
                    // 소지금(balance)에서 사이다의 가격(ciderPrice)만큼 감소
                    ciderHas++;
                    // 소지품의 사이다(ciderHas)를 증가
                    System.out.println("사이다를 구매합니다.\n사이다 +1개");
                }
            } else if (selectedMenu == 3) {
                // 입력된 번호가 매실차(3) 일 때,
                if (balance < maesilPrice) {
                    // 소지금(balance)이 매실차의 가격(maesilPrice)보다 부족한 경우
                    System.out.println("소지금이 부족합니다.");
                } else {
                    // 소지금(balance)이 충분한 경우
                    balance -= maesilPrice;
                    // 소지금(balance)에서 매실차의 가격(maesilPrice)만큼 감소
                    maesilHas++;
                    // 소지품의 매실차(maesilHas)를 증가
                    System.out.println("매실차를 구매합니다.\n매실차 +1개");
                }
            } else if (selectedMenu == 0) {
                // 입력된 번호가 소지품 확인(0) 일 때,
                // 소지금(balance)과 각 메뉴의 현재 소지하고 있는 개수를 표시
                // colaHas, ciderHas, maesilHas가 해당됨.
                System.out.println("=====소지품=====");
                System.out.println("소지금 : " + balance + "원");
                System.out.println("콜라 : " + colaHas + "개");
                System.out.println("사이다 : " + ciderHas + "개");
                System.out.println("매실차 : " + maesilHas + "개");
                System.out.println("===============");
            } else if (selectedMenu == 9) {
                // 입력된 번호가 종료(9) 일 때,
                ////break;
                ////(line:17)의 while(true)에서 탈출하여 프로그램 종료
                System.exit(0);
                // 현재 실행중인 프로그램을 즉시 종료시키는 명령
            } else {
                // 입력된 번호가 메뉴에 없는 번호일 경우, 안내문과 함께 메뉴 다시 제시
                System.out.println("알수없는 번호입니다.\n다시 입력해주세요.");
            }
        }
    }
}
