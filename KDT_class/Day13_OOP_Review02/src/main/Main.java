package main;

import dao.StudentDAO;
import dto.StudentDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();

        while (true) {
            System.out.println("<< 학생 관리 시스템 >>");
            System.out.println("1. 학생 정보 등록");
            System.out.println("2. 학생 목록 출력");
            System.out.println("3. 학생 정보 검색(이름)");
            System.out.println("4. 학생 정보 수정(ID)");
            System.out.println("5. 학생 정보 삭제(ID)");
            System.out.println("0. 시스템 종료");

            int selectedMenu = inputToInt(sc, ">> ");

            switch (selectedMenu) {
                case 1:
                    printDash();
                    studentDAO.regStudent(inputStudentInfo(sc));
                    System.out.println("등록이 완료되었습니다.");
                    printDash();
                    break;
                case 2:
                    printStudentList(studentDAO.getStudentList());
                    break;
                case 3:
                    printDash();
                    System.out.print("검색할 학생 이름: ");
                    String searchStr = sc.nextLine();

                    List<StudentDTO> studentList = studentDAO.findStudentByName(searchStr);

                    if(!studentList.isEmpty()) {
                        printStudentList(studentList);
                    } else {
                        System.out.println("출력할 학생 목록이 없습니다.");
                    }

                    break;
                case 4:
                    if(!printStudentList(studentDAO.getStudentList())){
                        break;
                    }
                    StudentDTO modifiedStudentInfo = modifyStudentInfo(sc, studentDAO, inputToInt(sc, "수정할 학생의 ID를 입력하세요: "));
                    if(modifiedStudentInfo != null) {
                        studentDAO.modifyStudentInfo(modifiedStudentInfo);
                        System.out.println("수정이 완료되었습니다.");
                    } else {
                        System.out.println("검색된 학생이 없습니다.");
                    }
                    printDash();
                    break;
                case 5:
                    if(!printStudentList(studentDAO.getStudentList())){
                        break;
                    }
                    if(studentDAO.removeStudent(inputToInt(sc, "삭제할 학생의 ID를 입력하세요: "))) {
                        System.out.println("삭제가 완료되었습니다.");
                    } else {
                        System.out.println("검색된 학생이 없습니다.");
                    }
                    printDash();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("메뉴에 없는 번호입니다.\n다시 입력해주세요.");
                    break;
            }
        }
    }

    //----------입력----------
    // 등록할 StudentDTO의 정보 입력받기
    public static StudentDTO inputStudentInfo(Scanner sc) {
        System.out.print("학생 이름: ");
        String name = sc.nextLine();
        int kor = inputToInt(sc, "국어 점수: ");
        int eng = inputToInt(sc, "영어 점수: ");
        int math = inputToInt(sc, "수학 점수: ");

        Date evaluatedDate;
        while(true) {
            try {
                System.out.print("평가일(yy/MM/dd): ");
                evaluatedDate = stringToDate(sc.nextLine());
                break;
            } catch(Exception e) {
                //e.printStackTrace();
                System.out.println("지정된 형식으로 다시 입력해주세요.");
            }
        }

        return new StudentDTO(name, kor, eng, math, evaluatedDate);
    }

    // 수정할 StudentDTO의 정보 입력받기
    public static StudentDTO modifyStudentInfo(Scanner sc, StudentDAO studentDAO, int targetId) {
        StudentDTO targetStudent = studentDAO.findStudentById(targetId);

        if(targetStudent == null){
            return null;
        }

        System.out.println("수정할 학생 정보 입력(공백입력시, 기존 정보 유지)");

        System.out.print("학생 이름(현재: "+ targetStudent.getName() +"): ");
        String name = sc.nextLine();
        if(name.isEmpty()) {
            name = targetStudent.getName();
        }

        int kor = inputStudentScore(sc, "국어 점수(현재: " + targetStudent.getKor() + "): ");
        if(kor == -1) {
            kor = targetStudent.getKor();
        }

        int eng = inputStudentScore(sc, "영어 점수(현재: " + targetStudent.getEng() + "): ");
        if(eng == -1) {
            eng = targetStudent.getEng();
        }

        int math = inputStudentScore(sc, "수학 점수(현재: " + targetStudent.getMath() + "): ");
        if(math == -1) {
            math = targetStudent.getMath();
        }

        Date publishDate;
        while(true) {
            try {
                System.out.print("평가일(yy/MM/dd)(현재: "+ dateToString(targetStudent.getEvaluatedDate(), "yy/MM/dd") +"): ");
                String dateStr = sc.nextLine();
                if(dateStr.isEmpty()) {
                    publishDate = targetStudent.getEvaluatedDate();
                    break;
                }
                publishDate = stringToDate(dateStr);
                break;
            } catch(Exception e) {
                //e.printStackTrace();
                System.out.println("지정된 형식으로 다시 입력해주세요.");
            }
        }

        return new StudentDTO(targetId, name, kor, eng, math, publishDate);
    }

    // modify 점수 입력 예외처리(inputMsg: 입력 콘솔 안내 문구)
    public static int inputStudentScore(Scanner sc, String inputMsg) {
        while(true) {
            try {
                System.out.print(inputMsg);
                String korStr = sc.nextLine();
                if (korStr.isEmpty()) {
                    return -1;
                } else {
                    return Integer.parseInt(korStr);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    // 숫자 입력 예외 처리(inputMsg: 입력 콘솔 안내 문구)
    public static int inputToInt(Scanner sc, String inputMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }
    //-----------------------

    //------------출력-------------
    // 파라미터로 받은 studentList의 정보 출력(false 반환시, 출력한 값이 없음.)
    public static boolean printStudentList(List<StudentDTO> studentList) {
        printDash();
        if(!studentList.isEmpty()) {
            System.out.println("학번\t\t이름\t국어\t영어\t수학\t합계\t평균\t\t평가일");
            for (StudentDTO student : studentList) {
                System.out.println(student.getId() + "\t" +
                        student.getName() + "\t" +
                        student.getKor() + "\t" +
                        student.getEng() + "\t" +
                        student.getMath() + "\t" +
                        student.getTotal() + "\t" +
                        student.getAvg() + "\t" +
                        dateToString(student.getEvaluatedDate(), "MM월 dd일"));
            }
            printDash();
            return true;
        } else {
            System.out.println("출력할 학생 목록이 없습니다.");
            printDash();
            return false;
        }
    }

    public static void printDash() {
        System.out.println("==================================");
    }
    //----------------------------

    //-------------utils-------------
    // String -> Date 날짜 변환
    public static Date stringToDate(String dateStr) throws Exception {
        SimpleDateFormat parseSdf = new SimpleDateFormat("yy/MM/dd");
        return parseSdf.parse(dateStr);
    }

    // Date -> String 날짜 변환
    public static String dateToString(Date date, String format) {
        SimpleDateFormat formatSdf = new SimpleDateFormat(format);
        return formatSdf.format(date);
    }
    //--------------------------------
}
