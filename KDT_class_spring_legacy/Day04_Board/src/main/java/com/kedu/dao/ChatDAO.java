package com.kedu.dao;

import com.kedu.dto.ChatDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    public void insert(ChatDTO dto) {
        mybatis.insert("Chat.insert", dto);
    }

    public List<ChatDTO> getChatHistory(){
        return mybatis.selectList("Chat.getChatHistory");
    }
}
