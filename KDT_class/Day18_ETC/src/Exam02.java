import java.util.HashMap;
import java.util.Map;

public class Exam02 {
    public static void main(String[] args) {
        Map<Integer, Student> stds = new HashMap<>();
        stds.put(1001, new Student(1001, "Tom", 80));
        stds.put(1002, new Student(1002, "Jane", 90));
        stds.put(1003, new Student(1003, "Susam", 60));

        System.out.println(stds.get(1002).getId() + " " + stds.get(1002).getName() + " " + stds.get(1002).getKor());
        System.out.println("------------------------");

        for(int key : stds.keySet()) {
            System.out.println(stds.get(key).getId() + " " + stds.get(key).getName() + " " + stds.get(key).getKor());
        }

        stds.get(1002).setName("Max");
        stds.remove(1002);
    }
}
