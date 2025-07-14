public class Exam04 {
    public static void main(String[] args) {
        int[] rand = new int[7];

        for (int i = 0; i < rand.length; i++) {
            rand[i] = (int) (Math.random() * 45 + 1);
            for (int j = 0; j < i; j++) {
                if (rand[i] == rand[j]) {
                    i--;
                    break;
                }
            }
        }

        for (int i = 0; i < rand.length; i++) {
            System.out.println(rand[i]);
        }
    }
}
