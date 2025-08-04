package dao;

import dto.MessagesDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {

    public void insMsg(String writer, String contact) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kedu", "kedu");

        String sql = "INSERT INTO messages values(messages_seq.nextval, ?, ?)";
        PreparedStatement pstat = con.prepareStatement(sql);

        pstat.setString(1, writer);
        pstat.setString(2, contact);

        pstat.executeUpdate();

        con.close();
        pstat.close();
    }

    public List<MessagesDTO> getMsg() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kedu", "kedu");

        String sql = "SELECT * FROM messages";
        PreparedStatement pstat = con.prepareStatement(sql);

        ResultSet rs = pstat.executeQuery();

        List<MessagesDTO> messageList = new ArrayList<>();


        while(rs.next()) {
            String seq = rs.getString("seq");
            String writer = rs.getString("writer");
            String message = rs.getString("message");

            messageList.add(new MessagesDTO(Integer.parseInt(seq), writer, message));
        }

        con.close();
        pstat.close();
        rs.close();

        return messageList;
    }
}
