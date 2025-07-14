package quiz;

import classes.StudentDTO;
import models.StudentDAO;

import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        for(int i = 0; i < studentDAO.getStudentDTOS().length; i++) {
            System.out.print((i + 1) + "번째 학생의 이름을 입력하세요 : ");
            String name = sc.nextLine();
            System.out.print(name + "학생의 국어 점수를 입력하세요 : ");
            int kor = Integer.parseInt(sc.nextLine());
            System.out.print(name + "학생의 영어 점수를 입력하세요 : ");
            int eng = Integer.parseInt(sc.nextLine());
            System.out.print(name + "학생의 수학 점수를 입력하세요 : ");
            int math = Integer.parseInt(sc.nextLine());

            studentDAO.addStudent(new StudentDTO(name, kor, eng, math));
        }

        // 3. 입력이 전부 끝나면, 학생 정보들을 취합하여
        // 이름, 국어, 영어, 수학, 합계, 평균을 화면에 출력할 것
        StudentDTO[] students = studentDAO.getStudentDTOS();
        for(int i = 0; i < students.length; i++) {
            System.out.println("이름 : " + students[i].getName() +
                                "  국어 : " + students[i].getKor() +
                                "  영어 : " + students[i].getEng() +
                                "  수학 : " + students[i].getMath() +
                                "  합계 : " + students[i].getTotal() +
                                "  평균 : " + students[i].getAvg());
        }
    }
}
