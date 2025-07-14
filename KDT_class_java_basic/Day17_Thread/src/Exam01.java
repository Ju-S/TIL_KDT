
class Worker extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            System.out.print(i + " ");
        }
    }
}

public class Exam01 {
    public static void main(String[] args) {

        // 쓰레드란?(Thread)
        // 담당 프로세스를 실행시키는 단위

        // 멀티쓰레드 구현을 위한 Steps
        // 1. Thread 클래스를 상속받는 사용자 정의 클래스를 구현한다.
        // 2. Thread 클래스로부터 상속받은 public void run() 매서드를 override 한다.
        // 3. 병렬처리를 원하는 코드를 run 내부에 작성한다.
        // 4. 작성된 사용자 정의 클래스를 바탕으로 인스턴스를 생성한다.
        // 5. 생성된 인스턴스로부터 start 메서드를 콜 한다.

        Worker w1 = new Worker();
        w1.start();

        Worker w2 = new Worker();
        w2.start();

        for(int i = 0; i < 100; i++) {
            System.out.print(i + " ");
        }
    }
}
