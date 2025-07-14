import java.util.Scanner;

public class Quiz2869 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int A = Integer.parseInt(str.split(" ")[0]);
        int B = Integer.parseInt(str.split(" ")[1]);
        int V = Integer.parseInt(str.split(" ")[2]);

        int currentHeight = 0;
        int spendDays = 0;

        while(currentHeight < V){
            if(currentHeight > 0){
                currentHeight -= B;
            }
            currentHeight += A;
            spendDays++;
        }

        System.out.println(spendDays);
    }
}
