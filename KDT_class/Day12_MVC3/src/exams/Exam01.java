package exams;

abstract class A{
    int a = 10;

    abstract public void testA();
}

class B extends A{
    int b = 10;

    @Override
    public void testA(){
        System.out.println("B");
    }
}

class C extends A{
    int c = 10;

    public void testC(){
        System.out.println("hi");
    }

    @Override
    public void testA(){
        System.out.println("C");
    }
}

public class Exam01 {
    public static void main(String[] args) {
        // 다형성(형상이 많은 성질)
        // 클래스 간 상속 관계에서 부모클래스의 참조변수는 자신을 상속받는 자식 클래스 인스턴스의 주소를 저장할 수 있다.

        A a = new C(); // up-casting

        //a.testC();  // 업캐스팅으로 인해 C 객체의 기능은 사용 못함.

        C c = (C)a; // down-casting

        c.testC();  // 다운캐스팅으로 기존 a의 변경내용 + c의 내용의 C 클래스를 사용한다.
    }
}
