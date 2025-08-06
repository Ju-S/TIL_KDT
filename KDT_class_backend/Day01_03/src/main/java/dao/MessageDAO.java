package dao;

import dto.MessageDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private static MessageDAO instance;
    private final DataSource ds;

    private MessageDAO() throws Exception {
        Context ctx = new InitialContext();
        this.ds = (DataSource) ctx.lookup("java:comp/env/jdbc/kedu");
    }

    public synchronized static MessageDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MessageDAO();
        }
        return instance;
    }

    public void deleteBySeq(int targetSeq) throws Exception {
        String sql = "DELETE FROM messages WHERE seq = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, targetSeq);
            pstat.executeUpdate();
        }
    }

    public void updateBySeq(int targetSeq, String writer, String message) throws Exception {
        String sql = "UPDATE messages SET writer = ?, message = ? WHERE seq = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, writer);
            pstat.setString(2, message);
            pstat.setInt(3, targetSeq);

            pstat.executeUpdate();
        }
    }

    public void insert(String writer, String message) throws Exception {
        String sql = "INSERT INTO messages VALUES(messages_seq.nextval, ?, ?)";
        try (Connection con = ds.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, writer);
            pstat.setString(2, message);

            pstat.executeUpdate();
        }
    }

    public List<MessageDTO> getMessages() throws Exception {
        String sql = "SELECT * FROM messages";
        List<MessageDTO> resultList = new ArrayList<>();

        try (Connection con = ds.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {

            while (rs.next()) {
                int seq = rs.getInt("seq");
                String writer = rs.getString("writer");
                String message = rs.getString("message");

                resultList.add(new MessageDTO(seq, writer, message));
            }

            return resultList;
        }
    }
}
