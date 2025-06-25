package quizes;

import classes.RentalDTO;
import models.RentalDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalDAO rentalDAO = new RentalDAO();

        while (true) {
            System.out.println("<< 렌트카 관리 시스템 >>");
            System.out.println("1. 신규 예약 등록");
            System.out.println("2. 예약 목록 출력");
            System.out.println("3. 예약 검색 ( 예약자 이름으로 검색)");
            System.out.println("4. 예약 삭제");
            System.out.println("5. 예약 수정");
            System.out.println("0. 시스템 종료");
            System.out.print(">> ");
            int selectedMenu = inputToInt(sc, "숫자로 입력해주세요.\n>> ");

            RentalDTO[] rentalDTOList = rentalDAO.getRentalDTOList();

            switch (selectedMenu) {
                case 1:
                    // 등록
                    for (int i = 0; i < rentalDTOList.length; i++) {
                        if (rentalDTOList[i] == null) {
                            rentalDAO.addRentalDTOToIndex(inputRegRentalInfo(sc), i);
                            System.out.println("예약 성공.");
                            break;
                        } else if (i == rentalDTOList.length - 1) {
                            System.out.println("최대 " + rentalDTOList.length + "건의 예약을 등록할 수 있습니다.");
                        }
                    }
                    break;
                case 2:
                    // 출력
                    printRentalList(rentalDAO, selectedMenu);
                    break;
                case 3:
                    // 검색
                    System.out.print("검색할 예약자 이름: ");
                    RentalDTO[] result = rentalDAO.findRentalByName(sc.nextLine());
                    RentalDAO resultDAO = new RentalDAO();
                    resultDAO.setRentalDTOList(result);
                    printRentalList(resultDAO, selectedMenu);
                    break;
                case 4:
                    // 삭제
                    printRentalList(rentalDAO, selectedMenu);
                    if (!rentalDAO.isEmptyRentalArray(rentalDTOList)) {
                        System.out.print("삭제할 예약 번호: ");
                        if(rentalDAO.removeRentalDTO(rentalDAO.findRentalById(inputToInt(sc, "숫자로 입력해주세요.\n삭제할 예약 번호: ")))) {
                            System.out.println("삭제 성공.");
                        } else {
                            System.out.println("존재하지 않는 예약번호 입니다.");
                        }
                    }
                    break;
                case 5:
                    // 수정
                    printRentalList(rentalDAO, selectedMenu);
                    if (!rentalDAO.isEmptyRentalArray(rentalDTOList)) {
                        System.out.print("수정할 예약 번호: ");
                        inputModifyRentalInfo(sc, rentalDAO, inputToInt(sc, "숫자로 입력해주세요.\n수정할 예약 번호: "));
                    }
                    break;
                case 0:
                    // 종료
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("없는 메뉴입니다.\n다시 입력해주세요.");
                    break;
            }
        }
    }

    //-----------시스템 기능-----------
    public static RentalDTO inputRegRentalInfo(Scanner sc) {
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

        return new RentalDTO(carId, name, rentalStartDate);
    }

    public static void inputModifyRentalInfo(Scanner sc, RentalDAO rentalDAO, int modifyRentalId) {
        RentalDTO targetRentalDTO = rentalDAO.getRentalDTOList()[rentalDAO.findRentalById(modifyRentalId)];

        System.out.print("예약자 이름(현재: " + targetRentalDTO.getName() + "): ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            name = targetRentalDTO.getName();
        }

        System.out.print("차번호(현재: " + targetRentalDTO.getCarId() + "): ");
        int carId = inputToInt(sc, "숫자로 입력해주세요.\n차번호(현재: " +
                targetRentalDTO.getCarId() + "): ");

        Date rentalStartDate;
        while (true) {
            try {
                System.out.print("대여 시작일(yyyy년MM월dd일)(현재: " +
                        dateToString(targetRentalDTO.getRentalStartDate()) +
                        "): ");
                String date = sc.nextLine();
                if (date.isEmpty()) {
                    rentalStartDate = targetRentalDTO.getRentalStartDate();
                } else {
                    rentalStartDate = stringToDate(date);
                }
                break;
            } catch (Exception e) {
                System.out.println("날짜형식에 맞춰 입력해주세요.");
            }
        }

        if (rentalDAO.modifyRentalDTOById(new RentalDTO(carId, name, rentalStartDate), modifyRentalId)) {
            System.out.println("수정 성공.");
        } else {
            System.out.println("존재하지 않는 예약번호 입니다.");
        }
    }

    public static void printRentalList(RentalDAO rentalDAO, int menuType) {
        RentalDTO[] rentalDTOList = rentalDAO.getRentalDTOList();

        System.out.println("===========================================");
        if (!rentalDAO.isEmptyRentalArray(rentalDTOList)) {
            System.out.println("예약번호\t예약자\t차번호\t시작일\t반환일");
            System.out.println("===========================================");
            for (int i = 0; i < rentalDTOList.length; i++) {
                if (rentalDTOList[i] != null) {
                    System.out.println(rentalDTOList[i].getId() + "\t" +
                            rentalDTOList[i].getName() + "\t" +
                            rentalDTOList[i].getCarId() + "\t" +
                            dateToString(rentalDTOList[i].getRentalStartDate()) + "\t" +
                            dateToString(rentalDTOList[i].getRentalEndDate()));
                }
            }
        } else {
            switch (menuType) {
                case 2, 4, 5:
                    System.out.println("예약된 차량이 없습니다.");
                    break;
                case 3:
                    System.out.println("검색된 차량이 없습니다.");
                    break;
            }
        }
        System.out.println("===========================================");
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
