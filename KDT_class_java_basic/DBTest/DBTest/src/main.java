import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class main {
    private static final int THREAD_COUNT = 100000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
//            new Thread(() -> sendRequest()).start();
            sendRequest();
        }
    }

    private static void sendRequest() {
        try {
            String encodedWriter = URLEncoder.encode("야옹", "UTF-8");
            String encodedMessage = URLEncoder.encode("멍멍", "UTF-8");

            String targetUrl = "http://10.5.5.8/input.user?name=" + encodedWriter + "&phonNumber=" + encodedMessage;
//            String targetUrl = "http://10.5.5.19/Quiz01?name=" + encodedWriter + "&contact=" + encodedMessage;
//            String targetUrl = "http://10.5.5.7/Exam01?writer=" + encodedWriter + "&message=" + encodedMessage;
//            String targetUrl = "http://10.5.5.9/Exam01?writer=" + encodedWriter + "&message=" + encodedMessage;
//            String targetUrl = "http://10.5.5.11/Exam01?writer=" + encodedWriter + "&message=" + encodedMessage;
//            String targetUrl = "http://10.5.5.1/Exam01?name=" + encodedWriter + "&phone=" + encodedMessage;
//            String targetUrl = "http://10.5.5.2/Servlet01?name=" + encodedWriter + "&phonenum=" + encodedMessage;
//            String targetUrl = "http://10.5.5.8/output.jsp";
//            String targetUrl = "http://localhost/Input?writer=" + encodedWriter + "&message=" + encodedMessage;
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
