package exams;

import java.time.LocalDate;

public class Exam01 {
    public static void main(String[] args) {
        // 프로그래밍 언어에서 날짜를 다루는 방법
        long date = System.currentTimeMillis();
        // 1970년 1월 1일부터 현 시점까지 흐른 시간을 밀리 초값으로 환산한 결과를 반환
        // UNIX Timestamp 라고 부른다.
        System.out.println(date);
    }
}
