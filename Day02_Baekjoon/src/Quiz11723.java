import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz11723 {
    static List<Integer> S = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "add" :
                    add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove" :
                    remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check" :
                    check(Integer.parseInt(st.nextToken()));
                    break;
                case "toggle" :
                    toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all" :
                    all();
                    break;
                case "empty" :
                    empty();
                    break;
                default:
                    break;
            }
        }
    }

    public static void add(int x) {
        if(!S.contains(x)) {
            S.add(x);
        }
    }

    public static void remove(int x) {
        if(S.contains(x)) {
            S.remove((Integer)x);
        }
    }

    public static void check(int x) {
        if(S.contains(x)) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }

    public static void toggle(int x) {
        if(S.contains(x)) {
            remove(x);
        }
        else {
            add(x);
        }
    }

    public static void all() {
        S.clear();
        for(int i = 0; i < 20; i++) {
            S.add(i + 1);
        }
    }

    public static void empty() {
        S.clear();
    }
}
