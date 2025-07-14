import java.util.Scanner;

public class Exam02 {
    public static void main(String[] args) {
        int[] arr = new int[3];
        String[] orderString = new String[]{"첫", "두", "세"};

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < arr.length; i++){
            System.out.print(orderString[i] + " 번째 수 : ");
            arr[i] = Integer.parseInt(sc.nextLine());
        }

        System.out.print("입력 받은 수는 ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if(i != arr.length - 1) {
                System.out.print(" : ");
            }
        }
    }
}
