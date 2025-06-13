public class Exam01 {
    public static void main(String[] args) {

        byte a = 10;
        short b = a; //좁은 범위의 자료 형이 넓은 범위의 자료 형에 대입 될 때 'Data type Promotion'

        int a2 = 20;
        byte b2 = (byte)a2; // 넓은 범위의 자료 형이 좁은 범위의 자료 형에 대입 될 때 'Data type Casting'
        // 캐스팅은 원본데이터 손실까지 감수하며 대입하는 문법
        // 기본자료형과 다르게 참조자료형은 데이터 손실이 없어서, 참조잘형에서 종종 사용 됨.

        System.out.println("정답 : " + (5 + 10)); // 정답 : 15
        System.out.println(3/2); // 1
        System.out.println(3.0/2); // 1.5 #소수점을 입력하면 기본으로 double형으로 들어간다.
        System.out.println(3f/2f); // 1.5

        byte b3 = 10;
        char ch = 'A';
        int i = 100;
        long l = 10000L;

        b = (byte)i;
        ch = (char)b;
        short s = (short)ch;
        float f = l; // casting필요없음.
        i = ch; // casting필요없음.

        int i1 = 10;
        int i2 = 20;
        float f1 = 3.14f;
        char c1 = 'A';
        char c2 = 'B';

        System.out.println("결과 : " + i1 + i2);
        System.out.println("결과 : " + (i1 + i2));
        System.out.println("결과 : " + i1 + f1);
        System.out.println("결과 : " + (i1 + f1));
        System.out.println("결과 : " + f1 + c1);
        System.out.println("결과 : " + (f1 + c1));
        System.out.println("결과 : " + c1 + c2);
        System.out.println("결과 : " + (c1 + c2));

        //30 #오답 - 1020으로 출력됨.
        //30
        //13.14 #오답 - 103.14로 출력됨.
        //13.14
        //3.14A
        //68.14
        //AB
        //131
    }
}
