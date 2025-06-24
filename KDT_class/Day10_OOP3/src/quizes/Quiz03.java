package quizes;

import classes.Rental;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Rental[] rentalList = new Rental[3];
        int regRentalCnt = 0;

        while (true) {
            System.out.println("<< 렌트카 관리 시스템 >>");
            System.out.println("1. 신규 예약 등록");
            System.out.println("2. 예약 목록 출력");
            System.out.println("3. 예약 검색 ( 예약자 이름으로 검색)");
            System.out.println("0. 시스템 종료");
            System.out.print(">> ");
            int selectedMenu = inputToInt(sc, "숫자로 입력해주세요.\n>> ");

            switch (selectedMenu) {
                case 1:
                    if(regRentalCnt < 3) {
                        rentalList[regRentalCnt++] = regRental(sc, regRentalCnt);
                        System.out.println("예약 성공.");
                    } else {
                        System.out.println("최대 3건의 예약을 등록할 수 있습니다.");
                    }
                    break;
                case 2:
                    printRentalList(rentalList, selectedMenu);
                    break;
                case 3:
                    System.out.print("검색할 예약자 이름: ");
                    Rental[] result = findRentalByName(rentalList, sc.nextLine());
                    printRentalList(result, selectedMenu);
                    break;
                case 0:
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("없는 메뉴입니다.\n다시 입력해주세요.");
                    break;
            }
        }
    }

    //-----------시스템 기능-----------

    public static Rental regRental(Scanner sc, int regRentalCnt) {
        int id = 1000 + regRentalCnt;

        System.out.print("예약자 이름: ");
        String name = sc.nextLine();

        System.out.print("차번호: ");
        int carId = inputToInt(sc, "숫자로 입력해주세요.\n차번호: ");

        Date rentalStartDate;
        while (true) {
            try {
                System.out.print("대여 시작일(yyyy년MM월dd일): ");
                rentalStartDate = stringToDate(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("날짜형식에 맞춰 입력해주세요.");
            }
        }

        return new Rental(id, carId, name, rentalStartDate);
    }

    public static void printRentalList(Rental[] rentalList, int menuType) {
        System.out.println("===========================================");
        if (rentalList[0] != null) {
            System.out.println("예약번호\t예약자\t차번호\t시작일\t반환일");
            System.out.println("===========================================");
            for (int i = 0; i < rentalList.length; i++) {
                if (rentalList[i] != null) {
                    System.out.println(rentalList[i].getId() + "\t" +
                            rentalList[i].getName() + "\t" +
                            rentalList[i].getCarId() + "\t" +
                            dateToString(rentalList[i].getRentalStartDate()) + "\t" +
                            dateToString(rentalList[i].getRentalEndDate()));
                }
            }
        } else {
            switch(menuType){
                case 2:
                    System.out.println("예약된 차량이 없습니다.");
                    break;
                case 3:
                    System.out.println("검색된 차량이 없습니다.");
                    break;
            }
        }
        System.out.println("===========================================");
    }

    public static Rental[] findRentalByName(Rental[] rentalList, String name) {
        Rental[] result = new Rental[3];

        for(int i = 0; i < rentalList.length; i++) {
            if(rentalList[i] != null) {
                if(rentalList[i].getName().contains(name)){
                    result[i] = rentalList[i];
                }
            }
        }

        return result;
    }

    //-----------Date <-> String-----------
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(date);
    }

    public static Date stringToDate(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
        return sdf.parse(str);
    }

    //------------입력 예외------------
    public static int inputToInt(Scanner sc, String errorMsg) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print(errorMsg);
            }
        }
    }
}
