package com.kedu.dao;

import com.kedu.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAO {

    @Autowired
    private JdbcTemplate jdbc;

    //region insert
    public void insertReply(ReplyDTO reply) {
        String sql = "INSERT INTO reply(seq, writer, contents, parent_seq) VALUES(reply_seq.nextval, ?, ?, ?)";
        jdbc.update(sql, reply.getWriter(), reply.getContents(), reply.getParentSeq());
    }
    //endregion

    //region select
    public List<ReplyDTO> selectReplyListByParentSeq(int targetParentSeq) {
        String sql = "SELECT * FROM reply WHERE parent_seq=? ORDER BY seq DESC";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(ReplyDTO.class), targetParentSeq);
    }
    //endregion

    //region update
    public void updateBySeq(ReplyDTO modifyInfo) {
        String sql = "UPDATE reply SET contents=? WHERE seq=?";
        jdbc.update(sql, modifyInfo.getContents(), modifyInfo.getSeq());
    }
    //endregion

    //region delete
    public void deleteBySeq(int target) {
        String sql = "DELETE FROM reply WHERE seq=?";
        jdbc.update(sql, target);
    }
    //endregion
}
