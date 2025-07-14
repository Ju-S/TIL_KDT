import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exam04 {
    public static void main(String[] args) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";

        try {
            Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stat = con.createStatement();

            String sql = "select * from cafe_menu";
            ResultSet result = stat.executeQuery(sql);

            while(result.next()) {
                System.out.print(result.getInt("id") + " ");
                System.out.print(result.getString("name") + " ");
                System.out.print(result.getInt("price") + " ");
                System.out.println(result.getString("iceable") + " ");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
