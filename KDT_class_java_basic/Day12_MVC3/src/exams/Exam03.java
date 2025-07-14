package exams;

import java.util.ArrayList;
import java.util.List;

public class Exam03 {
    public static void main(String[] args) {
        ArrayList arr = new ArrayList();
        arr.add("Hello");
        arr.add("Java");
        arr.add("Collection");
        //arr.add(1);
        arr.add(1, "asdf");

        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
        System.out.println(arr.get(2));

        System.out.println(arr.size());

        for(Object str : arr) {
            System.out.println(str + " : " + str.getClass().toString());
        }
    }
}
