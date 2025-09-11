package com.kedu.controllers;

import com.kedu.dao.MessagesDAO;
import com.kedu.dto.MessagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String inputProc(MessagesDTO dto) throws Exception {
        dao.insert(dto);
        return "redirect:/";
    }

    @RequestMapping("/output")
    public String toOutput(Model model) throws Exception {
        model.addAttribute("list", dao.list());
        return "messages/output";
    }

    @RequestMapping("/delete")
    public String delete(int target) throws Exception {
        dao.delete(target);
        return "redirect:/messages/output";
    }

    @RequestMapping("/update")
    public String update(MessagesDTO dto) throws Exception {
        dao.update(dto);
        return "redirect:/messages/output";
    }

    @ExceptionHandler
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "redirect:/error";
    }
}
