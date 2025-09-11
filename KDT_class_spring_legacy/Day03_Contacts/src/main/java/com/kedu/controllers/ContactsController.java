package com.kedu.controllers;

import com.google.gson.Gson;
import com.kedu.dao.ContactsDAO;
import com.kedu.dto.ContactsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsDAO dao;
    @Autowired
    private Gson gson;

    @RequestMapping("/insert")
    public String insert(ContactsDTO dto){
        try {
            dao.insert(dto);
            return "redirect:/";
        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    @RequestMapping("/register")
    public String register(){
        return "contacts/inputContacts";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        try {
            model.addAttribute("contactsList", gson.toJson(dao.getList()));
            return "contacts/outputContacts";
        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
