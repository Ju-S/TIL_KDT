package exams;

import classes.Tv;

public class Exam01 {
    public static void main(String[] args) {
        Tv tv = new Tv();

        tv.setChannel(10);
        System.out.println(tv);
        System.out.println(tv.getThis().getThis().getThis());
        // chaining 기법 : 메서드 호출 시 반환값을 인스턴스 자신의 주소로 반환하여 연속 호출을 지원
    }
}
