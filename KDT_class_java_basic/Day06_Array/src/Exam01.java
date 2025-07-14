import java.lang.reflect.Array;
import java.util.ArrayList;

public class Exam01 {
    public static void main(String[] args) {
        int[] arr = new int[4];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 1) * 10;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
