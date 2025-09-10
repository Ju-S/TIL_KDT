package com.kedu.controllers;

import com.kedu.dao.MessagesDAO;
import com.kedu.dto.MessagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesDAO dao;

    @RequestMapping("/input")
    public String toInput() {
        return "messages/input";
    }

    @RequestMapping("/inputproc")
    public String inputProc(MessagesDTO dto) {
        try {
            dao.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping("/output")
    public String toOutput() {

        return "messages/output";
    }
}
