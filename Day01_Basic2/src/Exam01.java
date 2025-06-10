
public class Exam01 {
    public static void main(String[] args) {
        byte aMax = 127;
        byte aMin = -128;
        short bMax = 32767;
        short bMin = -32768;
        char c = '가'; //ASCII코드에 따른 문자에 대응하는 수를 저장 + 국가별 코드표(eg, UTF-8 ···) (2byte)
        int dMax = 2147483647;
        int dMin = -2147483648;
        long eMax = 9223372036854775807L; //뒤에 'L'을 붙히면 long으로 인식
        long eMin = -9223372036854775808L;
    }
}
