import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam03 {
    // cafe mocha 를 삭제하는 코드 작성
    public static void main(String[] args) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";

        try {
            Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stat = con.createStatement();

            String sql = "delete from cafe_menu where name like 'Cafe Mocha'";
            int result = stat.executeUpdate(sql);

            if(result > 0) {
                System.out.println("메뉴 삭제 완료");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
