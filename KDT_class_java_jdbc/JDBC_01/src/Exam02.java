import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam02 {
    public static void main(String[] args) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";

        try {
            Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stat = con.createStatement();

            String sql = "update cafe_menu set price = 4800 where id = 1015";
            int result = stat.executeUpdate(sql);

            if(result > 0) {
                System.out.println("메뉴 수정 완료");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
