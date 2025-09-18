package com.kedu.services;

import com.kedu.dao.ChatDAO;
import com.kedu.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDAO chatDAO;

    public void insert(ChatDTO dto) {
        chatDAO.insert(dto);
    }

    public List<ChatDTO> getChatHistory() {
        return chatDAO.getChatHistory();
    }
}
