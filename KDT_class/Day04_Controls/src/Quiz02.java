import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long balance = 0L;
        int selectedMenu = 0;

        while (true) {
            System.out.println("<< ATM Simulator >>");
            System.out.println("1. 잔액조회");
            System.out.println("2. 입금하기");
            System.out.println("3. 출금하기");
            System.out.println("4. 종료하기");
            System.out.print(">> ");
            selectedMenu = Integer.parseInt(sc.nextLine());

            try {
                if (selectedMenu == 1) {
                    System.out.println("현재 잔액은 " + balance + "원 입니다.");
                } else if (selectedMenu == 2) {
                    System.out.print("얼마를 입금하시겠습니까? >> ");
                    balance += Long.parseLong(sc.nextLine());
                    System.out.println("입금을 완료하였습니다.");
                } else if (selectedMenu == 3) {
                    System.out.print("얼마를 출금하시겠습니까? >> ");
                    long wanted = Long.parseLong(sc.nextLine());
                    if (balance >= wanted) {
                        balance -= wanted;
                        System.out.println("출금을 완료하였습니다.");
                    } else {
                        System.out.println("출금이 불가합니다.(잔액부족)");
                    }
                } else if (selectedMenu == 4) {
                    break;
                } else {
                    System.out.println("선택된 번호는 없는 항목 입니다.");
                }
                System.out.println();
            } catch(Exception e){
                System.out.println("오류가 발생하였습니다.\n다시 시도해주세요.");
            }
        }
    }
}
