public class Quiz03 {
    public static void main(String[] args) {
        //3-1
        int a = 1325, b = 9327;
        System.out.println(a * b);

        //3-2
        long a1 = 10000000000L + 5000;
        System.out.println(a1);

        //3-3
        char c = 'A', d = 'B';
        System.out.println(Character.toString(c) + d);
        System.out.println("" + c + d);
    }
}
// 관계 연산자와 논리 연산자는 모두 true | false를 출력한다.
// & = ampersand, | = [vertical bar, pipeline]
// 논리연산자는 short cut 연산이 적용된다.