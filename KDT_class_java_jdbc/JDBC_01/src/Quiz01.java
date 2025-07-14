import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Quiz01 {
    public static void main(String[] args) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";
        // employee 테이블에서 사번, 이름, 연락처를 가져와 출력
        try {
            Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stat = con.createStatement();

            String sql = "select emp_id, emp_name, phone from employee";
            ResultSet result = stat.executeQuery(sql);

            while(result.next()) {
                System.out.print(result.getString("emp_id") + " ");
                System.out.print(result.getString("emp_name") + " ");
                System.out.println(result.getString("phone") + " ");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
