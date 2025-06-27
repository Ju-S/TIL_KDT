package exams;

public class Exam02 {
    public static void main(String[] args) {
        String[] arr = new String[10];
        arr[0] = "Hello";
        arr[1] = "Java";
        arr[2] = "Collection";

        arr[2] = null;

        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

        System.out.println(arr.length);
    }
}
