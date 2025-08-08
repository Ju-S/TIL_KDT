package dao;

import dto.MemberDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class MemberDAO {
    private static MemberDAO instance;

    private MemberDAO() {
    }

    public synchronized static MemberDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MemberDAO();
        }
        return instance;
    }

    private Connection getConnection() throws Exception {
        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/study");
        return ds.getConnection();
    }

    public boolean insertMember(MemberDTO member) throws Exception {
        String sql = "INSERT INTO members VALUES(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, member.getId());
            pstat.setString(2, member.getPw());
            pstat.setString(3, member.getName());
            pstat.setString(4, member.getPhone());
            pstat.setString(5, member.getEmail());
            pstat.setString(6, member.getZipcode());
            pstat.setString(7, member.getAddress1());
            pstat.setString(8, member.getAddress2());

            return pstat.executeUpdate() > 0;
        }
    }

    public boolean idDuplcheck(String id) throws Exception {
        String sql = "SELECT * FROM members WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, id);
            try (ResultSet rs = pstat.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean login(String id, String pw) throws Exception {
        String sql = "SELECT * FROM members WHERE id = ? AND pw = ?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, id);
            pstat.setString(2, pw);
            try (ResultSet rs = pstat.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean withdraw(String id) throws Exception {
        String sql = "DELETE FROM members WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, id);
            return pstat.executeUpdate() > 0;
        }
    }
}
