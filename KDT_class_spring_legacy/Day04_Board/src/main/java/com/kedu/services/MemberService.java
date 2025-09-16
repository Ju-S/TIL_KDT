package com.kedu.services;

import com.kedu.dao.MemberDAO;
import com.kedu.dto.MemberDTO;
import com.kedu.utils.CustomEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDAO memberDAO;

    public void insertMember(MemberDTO dto) {
        dto.setPw(CustomEncrypt.encrypt(dto.getPw()));
        memberDAO.insertMember(dto);
    }

    public boolean idDuplcheck(String id) {
        return memberDAO.idDuplcheck(id);
    }
}
