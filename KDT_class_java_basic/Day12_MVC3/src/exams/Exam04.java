package exams;

import java.util.ArrayList;
import java.util.List;

public class Exam04 {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("Hello");
        arr.add("World");
        arr.add("Java");

        //foreach(java-enhanced for)
        for(String str : arr) {
            System.out.println(str);
        }
    }
}
