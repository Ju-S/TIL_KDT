package com.kedu.services;

import com.kedu.dao.ReplyDAO;
import com.kedu.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyDAO replyDAO;

    public List<ReplyDTO> selectReplyListByParentSeq(int target){
        return replyDAO.selectReplyListByParentSeq(target);
    }
}
