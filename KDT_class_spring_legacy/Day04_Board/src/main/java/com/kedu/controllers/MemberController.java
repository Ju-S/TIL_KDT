package com.kedu.controllers;

import com.kedu.dao.MemberDAO;
import com.kedu.dto.MemberDTO;
import com.kedu.utils.CustomEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberDAO dao;

    @RequestMapping("/toregistry")
    public String registryForm() {
        return "member/registerForm";
    }

    @PostMapping("/register")
    public String registry(MemberDTO dto) {
        dto.setPw(CustomEncrypt.encrypt(dto.getPw()));
        dao.insertMember(dto);
        return "redirect:/";
    }
}
