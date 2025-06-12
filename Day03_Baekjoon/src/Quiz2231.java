import java.util.Scanner;

public class Quiz2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int finalResult = 0;

        for(int j = 1; j <= n; j++) {
            int result = j;
            for (int temp = j; temp > 0;) {
                result += temp % 10;
                temp /= 10;
            }
            if(result == n){
                finalResult = j;
                break;
            }
        }

        System.out.println(finalResult);
    }
}
