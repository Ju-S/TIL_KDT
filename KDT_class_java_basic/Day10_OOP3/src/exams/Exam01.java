package exams;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Exam01 {
    public static void main(String[] args) throws Exception{
        // 프로그래밍 언어에서 날짜를 다루는 방법
        long date = System.currentTimeMillis();
        // 1970년 1월 1일부터 현 시점까지 흐른 시간을 밀리 초값으로 환산한 결과를 반환
        // UNIX Timestamp 라고 부른다.
        System.out.println(date);

        // Timestamp <-> String 변환
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss:ms");
        String result = sdf.format(date); // String값
        // 24시간 == 86400000ms
        System.out.println(result);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss:ms");
        Date test = sdf2.parse(result);
        System.out.println(test.getTime());
    }
}
