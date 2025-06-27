package main;

import commons.MembershipGrade;
import dao.UserDAO;
import dto.GoldDTO;
import dto.Membership;
import dto.SilverDTO;
import dto.Topaz;

import java.util.List;
import java.util.Scanner;

// 객체지향 초급 문법
// Class / Instance / Getter & Setter / Constructor
// Default Constructor / this / 접근제한자(public, private, package...)
// static

// 객체지향 중급 문법
// 상속 / 추상화 / 다형성 / collection

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        // 현 회원 관리 시스템 프로젝트의 치명적 문제점 3가지

        // 코드 중복도 문제 -> 상속 문법으로 해결
        // 상속 > A 클래스에서 B 클래스로 A 클래스 내용을 전달하는(물려주는) 문법
        // is-a 을 성립시키는 상속만을 사용(e.g, Bear is a Animal, Tiger is a Animal)

        // 코드 결합도(의존도) 문제 -> 다형성 문법으로 해결
        // up-casting, down-casting

        // 데이터 저장소 문제 -> 배열을 collection 으로 대체하여 해결

        while (true) {
            System.out.println("<< 회원 관리 시스템 >>");
            System.out.println("1> 신규 회원 등록");
            System.out.println("2> 회원 목록 출력");
            System.out.println("3> 회원 검색 (이름으로 검색)");
            System.out.println("4> 회원 정보 삭제");
            System.out.println("5> 회원 정보 수정");
            System.out.println("0> 시스템 종료");
            int selectedMenu = inputToInt(sc, ">> ");

            printDash();  //"========="출력
            switch (selectedMenu) {
                case 1:  //신규 등록
                    if (userDAO.signup(inputSignupInfo(sc, userDAO))) {
                        printDash();
                        System.out.println("회원가입이 완료되었습니다.");
                    } else {
                        printDash();
                        System.out.println("회원가입을 실패하였습니다.");
                    }
                    break;
                case 2:  //출력
                    if (!userDAO.isEmpty()) {
                        printUserList(userDAO.getAllUsers());
                    } else {
                        System.out.println("등록되어있는 회원이 없습니다.");
                    }
                    break;
                case 3:  //이름검색
                    System.out.print("검색할 회원 이름: ");
                    List<Membership> foundUserList = userDAO.findUsersByName(sc.nextLine());

                    if (foundUserList != null) {
                        printDash();
                        printUserList(foundUserList);
                    } else {
                        System.out.println("검색된 회원이 없습니다.");
                    }
                    break;
                case 4:  //회원삭제
                    if (userDAO.removeUser(inputToInt(sc, "삭제할 회원 ID: "))) {
                        System.out.println("삭제가 완료되었습니다.");
                    } else {
                        System.out.println("삭제할 ID의 회원이 없습니다.");
                    }
                    break;
                case 5:  //회원정보수정
                    List<Membership> users = userDAO.getAllUsers();
                    int modifyTargetUserIndex = userDAO.findUserIndexById(inputToInt(sc, "수정할 회원 ID: "));

                    if (modifyTargetUserIndex != -1) {
                        userDAO.modifyUser(inputModifyInfo(sc, userDAO, users.get(modifyTargetUserIndex)), modifyTargetUserIndex);
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

    //입력값 List<Membership> 출력
    public static void printUserList(List<Membership> members) {
        for (Membership member : members) {
            System.out.println("ID: " + member.getId() +
                    "  이름: " + member.getName() +
                    "  등급: " + MembershipGrade.fromCode(member.getGrade()) +
                    "  누적 포인트: " + member.getPoint() +
                    "  보너스 포인트: " + member.getBonus());
        }
    }

    //회원 정보 입력(신규)
    public static Membership inputSignupInfo(Scanner sc, UserDAO userDAO) {
        int memberGrade = 0;
        int id;
        String name;
        double point;

        while (true) {
            memberGrade = inputToInt(sc, "멤버쉽 등급(" + MembershipGrade.getMembershipGradeList() + "): ");

            if (1 <= memberGrade && memberGrade < MembershipGrade.values().length) {
                break;
            } else {
                System.out.println("없는 등급입니다.\n다시입력해주세요.");
            }
        }

        while (true) {
            id = inputToInt(sc, "ID(숫자로 입력): ");
            if (userDAO.isIdDuplicated(id)) {
                System.out.println("이미 있는 ID 입니다.");
            } else {
                break;
            }
        }

        System.out.print("이름: ");
        name = sc.nextLine();
        point = inputToDouble(sc, "누적 포인트: ");

        return returnByGradeId(memberGrade, id, name, point);
    }

    //회원 정보 입력(수정)(수정하려는 user의 정보를 받아 기존값 표기)
    public static Membership inputModifyInfo(Scanner sc, UserDAO userDAO, Membership targetUserInfo) {
        int memberGrade;
        int id;
        String name;
        double point;

        while (true) {
            memberGrade = inputToInt(sc, "멤버쉽 등급(" + MembershipGrade.getMembershipGradeList() + ")(현재: " + MembershipGrade.fromCode(targetUserInfo.getGrade()) + "): ");

            if (1 <= memberGrade && memberGrade < MembershipGrade.values().length) {
                break;
            } else {
                System.out.println("없는 등급입니다.\n다시입력해주세요.");
            }
        }

        while (true) {
            id = inputToInt(sc, "ID(숫자로 입력)(현재: " + targetUserInfo.getId() + "): ");
            if (userDAO.isIdDuplicated(id) && targetUserInfo.getId() != id) {
                // 입력된 id가 중복되었더라도 기존 user와 id가 같다면 통과
                System.out.println("이미 있는 ID 입니다.");
            } else {
                break;
            }
        }

        System.out.print("이름(현재: " + targetUserInfo.getName() + "): ");
        name = sc.nextLine();
        point = inputToDouble(sc, "누적 포인트(현재: " + targetUserInfo.getPoint() + "): ");

        return returnByGradeId(memberGrade, id, name, point);
    }

    public static Membership returnByGradeId(int memberGrade, int id, String name, double point) {
        return switch (memberGrade) {
            case 1 -> new SilverDTO(id, name, point);
            case 2 -> new GoldDTO(id, name, point);
            case 3 -> new Topaz(id, name, point);
            default -> null;
        };
    }

    //int입력 예외처리(inputString = 숫자를 입력해주세요 다음의 입력 전 출력할 문구.
    public static int inputToInt(Scanner sc, String inputMsg) {
        while (true) {
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
        while (true) {
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
