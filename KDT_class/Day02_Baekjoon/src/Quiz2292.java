import java.util.Scanner;

public class Quiz2292 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //1 6(1 < n) 12(7 < n) 18(19 < n) 24(37 < n)
        int cnt = 0;
        int temp = 1;
        for (int i = 1; i <= n;) {
            cnt++;
            if(cnt == 1) i++;
            i += (cnt - 1) * 6;
        }
        System.out.println(cnt);
    }
}
