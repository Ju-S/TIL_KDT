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
        String sql = "INSERT INTO board VALUES(board_seq.nextval, ?, ?, ?, default, default)";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, postInfo.getWriter());
            pstat.setString(2, postInfo.getTitle());
            pstat.setString(3, postInfo.getContents());

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region select
    public List<BoardDTO> getPosts() throws Exception {
        String sql = "SELECT * FROM board ORDER BY seq DESC";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
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
    }

    public List<List<BoardDTO>> getPostsPagable(int itemPerPage) throws Exception {
        String sql = "SELECT * FROM board ORDER BY seq DESC";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
            List<List<BoardDTO>> posts = new ArrayList<>();

            while (true) {
                List<BoardDTO> temp = new ArrayList<>();
                for (int i = 0; i < itemPerPage; i++) {
                    if (!rs.next()) {
                        posts.add(temp);
                        return posts;
                    }
                    int seq = rs.getInt("seq");
                    String writer = rs.getString("writer");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    Timestamp writerDate = rs.getTimestamp("writer_date");
                    int viewCount = rs.getInt("view_count");

                    temp.add(new BoardDTO(seq, writer, title, contents, writerDate, viewCount));
                }
                posts.add(temp);
            }
        }
    }

    public List<BoardDTO> getPostsByOpt(String searchOpt, String target) throws Exception {
        String sql;

        if (searchOpt.equals("title")) {
            sql = "SELECT * FROM board WHERE title like ? ORDER BY seq DESC";
        } else {
            sql = "SELECT * FROM board WHERE writer like ? ORDER BY seq DESC";
        }

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            List<BoardDTO> posts = new ArrayList<>();
            pstat.setString(1, "%" + target + "%");
            try (ResultSet rs = pstat.executeQuery()) {
                while (rs.next()) {
                    int seq = rs.getInt("seq");
                    String writer = rs.getString("writer");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    Timestamp writerDate = rs.getTimestamp("writer_date");
                    int viewCount = rs.getInt("view_count");

                    posts.add(new BoardDTO(seq, writer, title, contents, writerDate, viewCount));
                }
            }
            return posts;
        }
    }

    public BoardDTO getPostBySeq(int target) throws Exception {
        String sql = "SELECT * FROM board WHERE seq=?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
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

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region update
    public boolean updatePostBySeq(BoardDTO target) throws Exception {
        String sql = "UPDATE board SET title=?, contents=? WHERE seq=?";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, target.getTitle());
            pstat.setString(2, target.getContents());
            pstat.setInt(3, target.getSeq());
            return pstat.executeUpdate() > 0;
        }
    }
    //endregion
}
