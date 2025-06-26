package main;

import dao.UserDAO;
import dto.SilverDTO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while(true) {
            System.out.println("<< 회원 관리 시스템 >>");
            System.out.println("1> 신규 회원 등록");
            System.out.println("2> 회원 목록 출력");
            System.out.println("3> 회원 검색 (이름으로 검색)");
            System.out.println("4> 회원 정보 삭제");
            System.out.println("5> 회원 정보 수정");
            System.out.println("0> 시스템 종료");
            int selectedMenu = inputToInt(sc, ">> ");

            printDash();
            switch(selectedMenu) {
                case 1:
                    if(userDAO.signup(inputSignupInfo(sc, userDAO))) {
                        printDash();
                        System.out.println("회원가입이 완료되었습니다.");
                    } else {
                        printDash();
                        System.out.println("회원가입을 실패하였습니다.");
                    }
                    break;
                case 2:
                    if(!userDAO.isEmpty()) {
                        printUserList(userDAO.getAllUsers());
                    } else {
                        System.out.println("등록되어있는 회원이 없습니다.");
                    }
                    break;
                case 3:
                    System.out.print("검색할 회원 이름: ");
                    SilverDTO[] foundUserList = userDAO.findUsersByName(sc.nextLine());

                    if(foundUserList != null) {
                        printDash();
                        printUserList(foundUserList);
                    } else {
                        System.out.println("검색된 회원이 없습니다.");
                    }
                    break;
                case 4:
                    if(userDAO.removeUser(inputToInt(sc, "삭제할 회원 ID: "))) {
                        System.out.println("삭제가 완료되었습니다.");
                    } else {
                        System.out.println("삭제할 ID의 회원이 없습니다.");
                    }
                    break;
                case 5:
                    SilverDTO[] users = userDAO.getAllUsers();
                    int modifyTargetUserIndex = userDAO.findUserIndexById(inputToInt(sc, "수정할 회원 ID: "));

                    if(modifyTargetUserIndex != -1) {
                        userDAO.modifyUser(inputModifyInfo(sc, userDAO, users[modifyTargetUserIndex]), modifyTargetUserIndex);
                        printDash();
                        System.out.println("수정이 완료되었습니다.");
                    } else {
                        System.out.println("수정할 ID의 회원이 없습니다.");
                    }
                    break;
                case 0:
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("없는 메뉴입니다.\n다시 선택해주세요.");
                    break;
            }
            printDash();
        }
    }

    //입력값 SilverDTO[] 출력
    public static void printUserList(SilverDTO[] userList) {
        for(int i = 0; i < userList.length; i++) {
            if(userList[i] != null) {
                System.out.println("ID: " + userList[i].getId() +
                                    "  이름: " + userList[i].getName() +
                                    "  누적 포인트: " + userList[i].getPoint() +
                                    "  보너스 포인트: " + userList[i].getBonus());
            }
        }
    }

    //회원 정보 입력(신규)
    public static SilverDTO inputSignupInfo(Scanner sc, UserDAO userDAO) {
        int id;
        String name;
        double point;

        while(true){
            id = inputToInt(sc, "ID(숫자로 입력): ");
            if(userDAO.isIdDuplicated(id)) {
                System.out.println("이미 있는 ID 입니다.");
            } else {
                break;
            }
        }

        System.out.print("이름: ");
        name = sc.nextLine();
        point = inputToDouble(sc, "누적 포인트: ");

        return new SilverDTO(id, name, point);
    }

    //회원 정보 입력(수정)(수정하려는 user의 정보를 받아 기존값 표기)
    public static SilverDTO inputModifyInfo(Scanner sc, UserDAO userDAO, SilverDTO targetUserInfo) {
        int id;
        String name;
        double point;

        while(true){
            id = inputToInt(sc, "ID(숫자로 입력)(현재: " + targetUserInfo.getId() + "): ");
            if(userDAO.isIdDuplicated(id) && targetUserInfo.getId() != id) {
                // 입력된 id가 중복되었더라도 기존 user와 id가 같다면 통과
                System.out.println("이미 있는 ID 입니다.");
            } else {
                break;
            }
        }

        System.out.print("이름(현재: " + targetUserInfo.getName() + "): ");
        name = sc.nextLine();
        point = inputToDouble(sc, "누적 포인트(현재: " + targetUserInfo.getPoint() + "): ");

        return new SilverDTO(id, name, point);
    }

    //int입력 예외처리(inputString = 숫자를 입력해주세요 다음의 입력 전 출력할 문구.
    public static int inputToInt(Scanner sc, String inputMsg) {
        while(true) {
            try {
                System.out.print(inputMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    //double입력 예외처리(inputString = 숫자를 입력해주세요 다음의 입력 전 출력할 문구.
    public static double inputToDouble(Scanner sc, String inputMsg) {
        while(true) {
            try {
                System.out.print(inputMsg);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    //단락 구분 print
    public static void printDash() {
        System.out.println("================================");
    }
}
