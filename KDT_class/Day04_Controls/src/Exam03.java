public class Exam03 {
    public static void main(String[] args) {
        int i;

        for (i = 1; i <= 10; i++) {
            if (i == 2 || i == 5) {
                continue;
            }
            System.out.println("i = " + i);
            if (i == 8) {
                break;
            }
        }

        //1 3 4 6 7 8
    }
}
