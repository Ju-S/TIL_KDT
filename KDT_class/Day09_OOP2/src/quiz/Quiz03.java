package quiz;

import classes.Student;

import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        // 1. 이름, 국어, 영어, 수학 항목을 멤버필드로 가지는
        // Student 클래스를 설계하되, setter/getter/constructor 를 구현할 것
        Student[] students = new Student[3];

        // 2. 제작한 Student 클래스를 활용하여, 3명의 학생정보를 스캐너로 입력받아 보관할 것
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < students.length; i++) {
            System.out.print((i + 1) + "번째 학생의 이름을 입력하세요 : ");
            String name = sc.nextLine();
            System.out.print(name + "학생의 국어 점수를 입력하세요 : ");
            int kor = Integer.parseInt(sc.nextLine());
            System.out.print(name + "학생의 영어 점수를 입력하세요 : ");
            int eng = Integer.parseInt(sc.nextLine());
            System.out.print(name + "학생의 수학 점수를 입력하세요 : ");
            int math = Integer.parseInt(sc.nextLine());

            students[i] = new Student(name, kor, eng, math);
        }

        // 3. 입력이 전부 끝나면, 학생 정보들을 취합하여
        // 이름, 국어, 영서, 수학, 합계, 평균을 화면에 출력할 것
        for(Student student : students) {
            int total, totalSubject = 3;
            System.out.println("이름 : " + student.getName() +
                                "  국어: " + student.getKor() +
                                "  영어: " + student.getEng() +
                                "  수학: " + student.getMath() +
                                "  합계: " + (total = student.getKor() + student.getEng() + student.getMath()) +
                                "  평균: " + ((double)total / totalSubject));
        }
    }
}
