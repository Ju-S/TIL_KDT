public class Exam04 {
    public static void main(String[] args) {
        // 난수 - random number

        double rand = Math.random(); // 0-1 사이의 예측할 수 없는 double 형 난수를 발생

        // 가정1. 1-10사이의 난수
        int max = 8;
        int result = (int)(Math.random() * max) + 37;

        // 최소: x, 최대: y 일 때,
        // random() * (y - x + 1) + x
        // 위의 공식은 x-y 범위의 난수를 얻는 공식이다

        System.out.println(result);
    }
}
