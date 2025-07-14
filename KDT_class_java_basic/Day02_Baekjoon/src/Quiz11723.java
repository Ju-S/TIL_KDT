import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Quiz11723 {
    private static final int MAX_NUMBER = 20;
    private static final Set<Integer> numbers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            processCommand(st);
        }
    }

    private static void processCommand(StringTokenizer st) {
        String command = st.nextToken();
        switch (command) {
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
        }
    }

    private static void add(int x) {
        numbers.add(x);
    }

    private static void remove(int x) {
        numbers.remove(x);
    }

    private static void check(int x) {
        System.out.println(numbers.contains(x) ? 1 : 0);
    }

    private static void toggle(int x) {
        if (numbers.contains(x)) {
            numbers.remove(x);
        } else {
            numbers.add(x);
        }
    }

    private static void all() {
        numbers.clear();
        for (int i = 1; i <= MAX_NUMBER; i++) {
            numbers.add(i);
        }
    }

    private static void empty() {
        numbers.clear();
    }
}