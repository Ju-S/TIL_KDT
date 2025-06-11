public class Quiz04 {
    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        System.out.println(++a == 10 && b++ == 21); // shortcut으로 ++a만 실행되고 b++은 생략한다. 따라서 이 줄 뒤에서는 a = 11, b = 20이 된다.
        System.out.println(a + " : " + b);

    }
}
