
class Exam02Worker extends Thread {

    String target = "";

    public Exam02Worker(String target) {
        this.target = target;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            System.out.print(target + " ");
        }
    }
}

public class Exam02 {
    public static void main(String[] args) {
        // #을 100개 출력하는 코드
        // $를 100개 출력하는 코드
        // *를 100개 출력하는 코드
        // 를 전부 병렬로 실행.

        Exam02Worker w1 = new Exam02Worker("#");
        Exam02Worker w2 = new Exam02Worker("$");
        Exam02Worker w3 = new Exam02Worker("*");

        w1.start();
        w2.start();
        w3.start();
    }
}
