import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exam01 {
    public static void main(String[] args) {
        // 1. DBMS 에 접속
        // 2. 로그인
        // 3. 쿼리 전송

        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";

        try {
            Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stat = con.createStatement();

//            stat.executeUpdate(); // - update, delete, insert와 같이 db에 변화를 주는 작업
//            stat.executeQuery(); // - select 처럼 db에 변화가 없는 직업
//            두 메서드는 반환값이 달라 쿼리에 따른 적절한 메서드를 사용해야 한다.
            //ResultSet result = stat.executeQuery("select * from cafe_menu");

            String sql = "insert into cafe_menu values(cafe_seq.nextval, 'banana smothie', 5500, 'y')";
            int result = stat.executeUpdate(sql);

            if(result > 0) {
                System.out.println("메뉴 입력 완료");
            }
            // con.commit() - auto 커밋이라 쿼리 날리면 자동으로 commit됨.
            con.close();  // 안닫으면 나중에 세션 많아져서 터짐 주의.
            // dbms = oracleDB, developer = dbms IDE 같은 느낌?

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}