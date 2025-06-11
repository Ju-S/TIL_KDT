
public class Exam01 {
    public static void main(String[] args) {
        //------정수형------
        byte aMax = 127;
        byte aMin = -128;
        short bMax = 32767;
        short bMin = -32768;
        char c = '가'; //ASCII코드에 따른 문자에 대응하는 수를 저장 + 국가별 코드표(eg, UTF-8 ···) (2byte)
        int dMax = 2147483647;
        int dMin = -2147483648;
        long eMax = 9223372036854775807L; //뒤에 'L'을 붙히면 long으로 인식
        long eMin = -9223372036854775808L;

        //------실수형------
        float f = 3.14f;
        double g = 5.12;
        // 실수형은 '무조건' 정수형보다 큼.
        // 정수끼리의 크기는 다른 정수형의 표현범위를 포함하냐를 따짐.
        // unsigned의 경우 크기가 다르다고 표현함.

        //------논리형------
        boolean h = true;

        //------참조형------ #크고 가변적인 데이터를 관리 #객체의 주소를 저장하여 변수의 크기는 주소를 담을 수만 있으면 됨.
        String i = "Hello";
    }
}
