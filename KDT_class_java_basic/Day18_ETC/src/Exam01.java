import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Exam01 {
    public static void main(String[] args) {
        // Collection
        // Array / ArrayList / LinkedList
        // Set / Map

        // Map
        // 1. Key / Value 한쌍의 구조로 데이터를 저장함
        // 2. 데이터 저장에 순서를 유지하지 않음.
        // 3. 중복 키값을 허용하지 않음.
        Map<Integer, String> data = new LinkedHashMap<>();
        data.put(1, "One");
        data.put(2, "Two");
        data.put(3, "Three");

        //data.remove(2);`12234567890987654DFGRETRDGFDYTRHGUYTFDKtKFKQDK ZHFFHAQLDKDFGDTHGFHYRCGFUYTVNFGJYTJHVBNVHJFGYUTHJJFTYRFJHTYJFUYTHFHGFRTRHDGFHFGDFTYRHDHGRTRGFDHDTRHGDFHGRTRHFHGFTRFHYTRHFHRTRHFDGDEWRCSFDS

        data.put(4, "Three");

        for(int key : data.keySet()) {
            System.out.println(key + " : " + data.get(key));
        }
    }
}
