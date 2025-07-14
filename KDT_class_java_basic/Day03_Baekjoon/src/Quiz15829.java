import java.util.Scanner;

public class Quiz15829 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int l = sc.nextInt();
        String s = sc.next();

        long hash = s.charAt(0) - 96;

        for(int i = 1; i < l; i++){
            long hashAlpha = s.charAt(i) - 96;
            long temp = hashAlpha;
            for(int j = 0; j < i; j++){ // 자바에서는 오버플로우 발생. 오버플로우를 방지하기 위해 수를 작게 유지한 상태로 연산.
                temp *= 31;
                temp %= 1234567891L;
            }
            hash += temp;
            hash %= 1234567891L;
        }

        System.out.println(hash);
    }
}
