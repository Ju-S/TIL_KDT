package dao;

import dto.BoardDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    //region initial methods
    private static BoardDAO instance;

    private BoardDAO() {
    }

    public synchronized static BoardDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new BoardDAO();
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
    public boolean posting(BoardDTO postInfo) throws Exception {
        String sql = "INSERT INTO board VALUES(?, ?, ?, ?, default, default)";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, postInfo.getSeq());
            pstat.setString(2, postInfo.getWriter());
            pstat.setString(3, postInfo.getTitle());
            pstat.setString(4, postInfo.getContents());

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region select
    public int getBoardSeqNextVal() throws Exception {
        String sql = "SELECT board_seq.nextval FROM dual";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public List<BoardDTO> getPostsPage(int curPage, int itemPerPage) throws Exception {
        String sql = "SELECT * FROM (SELECT board.*, ROW_NUMBER() OVER(ORDER BY seq DESC) rn FROM board) WHERE rn BETWEEN ? AND ?";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, curPage * itemPerPage - (itemPerPage - 1));
            pstat.setInt(2, curPage * itemPerPage);
            try (ResultSet rs = pstat.executeQuery()) {
                return getPostsPageableByResultSet(rs);
            }
        }
    }

    public List<BoardDTO> getPostsPage(String searchOpt, String target, int curPage, int itemPerPage) throws Exception {
        String sql = "SELECT * FROM (SELECT board.*, ROW_NUMBER() OVER(ORDER BY seq DESC) rn FROM board WHERE " + searchOpt + " like ? ORDER BY seq DESC) WHERE rn BETWEEN ? AND ?";

        if (!sql.isEmpty()) {
            try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
                pstat.setString(1, "%" + target + "%");
                pstat.setInt(2, curPage * itemPerPage - (itemPerPage - 1));
                pstat.setInt(3, curPage * itemPerPage);
                try (ResultSet rs = pstat.executeQuery()) {
                    return getPostsPageableByResultSet(rs);
                }
            }
        }
        return null;
    }

    private List<BoardDTO> getPostsPageableByResultSet(ResultSet rs) throws Exception {
        List<BoardDTO> posts = new ArrayList<>();
        while (rs.next()) {
            int seq = rs.getInt("seq");
            String writer = rs.getString("writer");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            Timestamp writerDate = rs.getTimestamp("writer_date");
            int viewCount = rs.getInt("view_count");
            posts.add(new BoardDTO(seq, writer, title, contents, writerDate, viewCount));
        }
        return posts;
    }

    public int getMaxPage(int itemPerPage) throws Exception {
        String sql = "SELECT count(*) FROM board";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
            if (rs.next()) {
                return (rs.getInt(1) - 1) / itemPerPage + 1;
            }
            return 0;
        }
    }

    public int getMaxPage(String searchOpt, String search, int itemPerPage) throws Exception {
        String sql = "SELECT count(*) FROM board WHERE " + searchOpt + " like ?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, "%" + search + "%");
            try (ResultSet rs = pstat.executeQuery()) {
                if (rs.next()) {
                    return (rs.getInt(1) - 1) / itemPerPage + 1;
                }
                return 0;
            }
        }
    }

    public BoardDTO getPostBySeq(int target) throws Exception {
        String sql = "SELECT * FROM board WHERE seq=?";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            try (ResultSet rs = pstat.executeQuery()) {
                if (rs.next()) {
                    String writer = rs.getString("writer");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    Timestamp writerDate = rs.getTimestamp("writer_date");
                    int viewCount = rs.getInt("view_count");

                    return new BoardDTO(target, writer, title, contents, writerDate, viewCount);
                }
                return null;
            }
        }
    }
    //endregion

    //region delete
    public boolean deletePostBySeq(int target) throws Exception {
        String sql = "DELETE FROM board WHERE seq=?";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region update
    public boolean updatePostBySeq(BoardDTO target) throws Exception {
        String sql = "UPDATE board SET title=?, contents=? WHERE seq=?";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, target.getTitle());
            pstat.setString(2, target.getContents());
            pstat.setInt(3, target.getSeq());
            return pstat.executeUpdate() > 0;
        }
    }

    public boolean updatePostsViewCntBySeq(int target) throws Exception {
        String sql = "UPDATE board SET view_count = view_count+1 WHERE seq=?";

        try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            return pstat.executeUpdate() > 0;
        }
    }
    //endregion
}
