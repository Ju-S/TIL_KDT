package server.dao;

import server.config.DataSourceProvider;
import server.dto.PostDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class PostDAO {
    // 방명록 조회
    public Map<Integer, PostDTO> getPosts() throws Exception {
        String sql = "select * from post order by created_at desc";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            return selectPost(stat);
        }
    }

    // 방명록 검색(author로 검색, author는 name 이다.)
    public Map<Integer, PostDTO> findContentsByAuthor(String author) throws Exception {
        String sql = "select * from post where author_name like ? order by created_at desc";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "%" + author + "%");
            return selectPost(stat);
        }
    }

    // 방명록 검색(authorId로 검색, author는 id 이다.)
    public Map<Integer, PostDTO> findContentsByAuthorId(String authorId) throws Exception {
        String sql = "select * from post where author_id like ? order by created_at desc";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "%" + authorId + "%");
            return selectPost(stat);
        }
    }

    public PostDTO findContentsById(int id) throws Exception {
        String sql = "select * from post where id = ?";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setInt(1, id);
            return selectPost(stat).get(id);
        }
    }

    public boolean isOwnedPost(String authorId, int targetId) throws Exception {
        String sql = "select * from post where author_id = ? and id = ?";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, authorId);
            stat.setInt(2, targetId);
            return !selectPost(stat).isEmpty();
        }
    }

    public boolean modifiyngContents(String modifiedContents, int targetId) throws Exception {
        String sql = "update post set contents = ? where id = ?";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, modifiedContents);
            stat.setInt(2, targetId);
            return stat.executeUpdate() > 0;
        }
    }

    private Map<Integer, PostDTO> selectPost(PreparedStatement stat) throws Exception {
        try(ResultSet rs = stat.executeQuery()) {
            Map<Integer, PostDTO> posts = new HashMap<>();
            while(rs.next()) {
                posts.put(rs.getInt("id"),
                        new PostDTO(rs.getString("contents"),
                                rs.getString("author_name"),
                                rs.getString("author_id"),
                                rs.getDate("created_at")));
            }
            return posts;
        }
    }

    // 방명록 생성
    public int addContents(PostDTO post) throws Exception {
        String sql = "insert into post values(post_seq.nextval, ?, ?, ?, default)";
        try(Connection con = DataSourceProvider.getInstance().getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, post.getAuthorId());
            stat.setString(2, post.getAuthor());
            stat.setString(3, post.getContents());
            return stat.executeUpdate();
        }
    }

    // 방명록 삭제(나의 name이 author로 등록된 글만 삭제 가능.)
    public boolean deletePost(int targetId, String loginUserId) throws Exception {
        if(!findContentsByAuthorId(loginUserId).isEmpty()) {
            String sql = "delete from post where id = ?";
            try (Connection con = DataSourceProvider.getInstance().getConnection();
                 PreparedStatement stat = con.prepareStatement(sql)) {
                stat.setInt(1, targetId);
                return stat.executeUpdate() > 0;
            }
        } else {
            return false;
        }
    }
}
