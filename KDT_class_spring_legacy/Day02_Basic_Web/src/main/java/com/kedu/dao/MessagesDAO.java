package com.kedu.dao;

import com.kedu.dto.MessagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessagesDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void insert(MessagesDTO dto) {
        String sql = "INSERT INTO messages(seq, writer, message) VALUES(messages_seq.nextval, ?, ?)";
        jdbc.update(sql, dto.getWriter(), dto.getMessage());
    }

    public List<MessagesDTO> list() throws Exception {
        String sql = "SELECT * FROM messages";
//        return jdbc.query(sql, new BeanPropertyRowMapper<>(MessagesDTO.class));

        return jdbc.query(
                sql, (rs, rowNum) ->
                        MessagesDTO.builder()
                                .seq(rs.getInt("seq"))
                                .writer(rs.getString("writer"))
                                .message(rs.getString("message"))
                                .build());

//        return jdbc.query(sql, new RowMapper<MessagesDTO>() {
//            @Override
//            public MessagesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return MessagesDTO.builder()
//                        .seq(rs.getInt("seq"))
//                        .writer(rs.getString("writer"))
//                        .message(rs.getString("message"))
//                        .build();
//            }
//        });
    }

    public void delete(int id) {
        String sql = "DELETE FROM messages WHERE seq=?";
        jdbc.update(sql, id);
    }

    public void update(MessagesDTO dto) {
        String sql = "UPDATE messages SET writer=?, message=? WHERE seq=?";
        jdbc.update(sql, dto.getWriter(), dto.getMessage(), dto.getSeq());
    }
}
