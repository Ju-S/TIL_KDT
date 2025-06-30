
// static - 정적 요소
public class Exam05 {
    public int a;           // 인스턴스 멤버 변수
    public static int b = 10;    // 클래스 멤버 변수

    public void funcA() {
        a = 10;
        b = 20;
    }

    public static void funcB() {
        //a = 10;
        b = 20;
    }

    public static void main(String[] args) {

        Exam05 e1 = new Exam05();
        Exam05 e2 = new Exam05();

        e1.b = 10;
        e2.b = 20;

        System.out.println(e1.b);

        funcB();
        e1.funcA();
//      funcA(); - 비 static 메서드이기 때문.
    }
}
