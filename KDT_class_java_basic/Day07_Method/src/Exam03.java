public class Exam03 {
    public static void main(String[] args) {
        String str = "Hello";
        int result = str.length();

        System.out.println(result);

        System.out.println(str.charAt(1));
        System.out.println(str.indexOf("e"));
        System.out.println(str.startsWith("H"));
        System.out.println(str.contains("e"));
    }
}
