package dao;

import dto.CafeDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafeDAO{
    private Connection getConnection() throws Exception{
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "kedu";
        String dbPassword = "kedu";
        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }

    public List<CafeDTO> selectAll() throws Exception {
        String sql = "select * from cafe_menu order by id";

        try(Connection con = this.getConnection();
                Statement stat = con.createStatement();
                ResultSet result = stat.executeQuery(sql);) {

            List<CafeDTO> tempList = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int price = result.getInt(3);
                String iceable = result.getString(4);
                tempList.add(new CafeDTO(id, name, price, iceable));
            }
            return tempList;
        }
    }

    public int insert(CafeDTO cafeDTO) throws Exception {
        // try with resources : 소괄호안의 모든 변수의 close를 반드시 실행해준다.
        try(Connection con = this.getConnection();
                Statement stat = con.createStatement();) {
            String sql = "insert into cafe_menu values(cafe_seq.nextval, '" + cafeDTO.getName() + "', " + cafeDTO.getPrice() + ", '" + cafeDTO.getIceable() + "')";
            return stat.executeUpdate(sql);
        }
    }

    public int update(CafeDTO cafeDTO) throws Exception {
        try(Connection con = this.getConnection();
                Statement stat = con.createStatement();) {
            String sql = "update cafe_menu set price = " + cafeDTO.getPrice() + " where id = " + cafeDTO.getId();
            return stat.executeUpdate(sql);
        }
    }

    public int delete(int id) throws Exception {
        try(Connection con = this.getConnection();
                Statement stat = con.createStatement();) {
            String sql = "delete from cafe_menu where id = " + id;
            return stat.executeUpdate(sql);
        }
    }
}
