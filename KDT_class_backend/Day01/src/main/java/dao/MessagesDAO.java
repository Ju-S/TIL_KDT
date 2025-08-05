package dao;

import dto.MessagesDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {
    private static MessagesDAO instance;

    public synchronized static MessagesDAO getInstance() {
        if(instance == null) {
            instance = new MessagesDAO();
        }
        return instance;
    }

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }
    // ioc: inversion of control

    public void insMsg(String writer, String contact) throws Exception {
        String sql = "INSERT INTO messages values(messages_seq.nextval, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, writer);
            pstat.setString(2, contact);

            pstat.executeUpdate();
        }
    }

    public List<MessagesDTO> getMsg() throws Exception {
        String sql = "SELECT * FROM messages";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
            List<MessagesDTO> messageList = new ArrayList<>();

            while (rs.next()) {
                String seq = rs.getString("seq");
                String writer = rs.getString("writer");
                String message = rs.getString("message");

                messageList.add(new MessagesDTO(Integer.parseInt(seq), writer, message));
            }
            return messageList;
        }
    }

    public void deleteMsg(int target) throws Exception {
        String sql = "DELETE FROM messages WHERE seq=?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            pstat.executeUpdate();
        }
    }

    public void updateMsg(int target, String writer, String contact) throws Exception {
        String sql = "UPDATE messages SET writer = ?, message = ? WHERE seq = ?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, writer);
            pstat.setString(2, contact);
            pstat.setInt(3, target);
            pstat.executeUpdate();
        }
    }
}
