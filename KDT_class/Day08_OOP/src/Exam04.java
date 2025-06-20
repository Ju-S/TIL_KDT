import it.sauronsoftware.ftp4j.FTPClient;

import java.io.File;
import java.io.FileOutputStream;

public class Exam04 {
    // FTP (File Transfer Protocol)
    public static void main(String[] args) throws Exception {
        FTPClient client = new FTPClient();
        client.connect("10.5.5.20", 21);
        client.login("java", "1234");

        for(int i = 0; i < 100; i++) {
            try {
                client.upload(new File("C:\\20250609_KDT\\download\\download" + i + ".txt"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        client.disconnect(true);
    }
}
