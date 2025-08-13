package dao;

import dto.ReplyDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO {

    //region initial methods
    private static ReplyDAO instance;

    private ReplyDAO() {
    }

    public synchronized static ReplyDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new ReplyDAO();
        }
        return instance;
    }

    private Connection getConnection() throws Exception {
        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/study");
        return ds.getConnection();
    }
    //endregion

    //region insert
    public boolean insertReply(ReplyDTO reply) throws Exception {
        String sql = "INSERT INTO reply VALUES(reply_seq.nextval, ?, ?, default, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, reply.getWriter());
            pstat.setString(2, reply.getContents());
            pstat.setInt(3, reply.getParentSeq());

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region select
    public List<ReplyDTO> selectReplyListByParentSeq(int targetParentSeq) throws Exception {
        String sql = "SELECT * FROM reply WHERE parent_seq=? ORDER BY seq DESC";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, targetParentSeq);
            try (ResultSet rs = pstat.executeQuery()) {
                List<ReplyDTO> resultList = new ArrayList<>();
                while (rs.next()) {
                    int seq = rs.getInt("seq");
                    String writer = rs.getString("writer");
                    String contents = rs.getString("contents");
                    Timestamp writeDate = rs.getTimestamp("write_date");
                    int parentSeq = rs.getInt("parent_seq");

                    resultList.add(new ReplyDTO(seq, writer, contents, writeDate, parentSeq));
                }
                return resultList;
            }
        }
    }
    //endregion

    //region update
    public boolean updateBySeq(ReplyDTO modifyInfo) throws Exception {
        String sql = "UPDATE reply SET contents=? WHERE seq=?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, modifyInfo.getContents());
            pstat.setInt(2, modifyInfo.getSeq());

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region delete
    public boolean deleteBySeq(int target) throws Exception {
        String sql = "DELETE FROM reply WHERE seq=?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion
}
