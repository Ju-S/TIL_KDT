package com.kedu.dao;

import com.kedu.dto.ReplyDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    //region insert
    public void insertReply(ReplyDTO reply) {
        mybatis.insert("Reply.insert", reply);
    }
    //endregion

    //region select
    public List<ReplyDTO> selectReplyListByParentSeq(int targetParentSeq) {
        return mybatis.selectList("Reply.select", targetParentSeq);
    }
    //endregion

    //region update
    public void updateBySeq(ReplyDTO modifyInfo) {
        mybatis.update("Reply.update", modifyInfo);
    }
    //endregion

    //region delete
    public void deleteBySeq(int target) {
        mybatis.delete("Reply.delete", target);
    }
    //endregion
}
