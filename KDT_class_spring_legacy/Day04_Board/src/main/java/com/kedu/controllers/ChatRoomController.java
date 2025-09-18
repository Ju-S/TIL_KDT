package com.kedu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chatroom")
public class ChatRoomController {

    @RequestMapping("/free")
    public String freeChat() {
        return "/chat/chatRoom";
    }
}
