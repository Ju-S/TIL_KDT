package com.kedu.dao;

import com.kedu.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MemberDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    //region insert
    public void insertMember(MemberDTO member) {
        mybatis.insert("Member.registry", member);
    }
    //endregion

    //region select
    public boolean idDuplcheck(String id) {
        return mybatis.selectOne("Member.idDuplCheck", id);
    }

    public boolean login(Map<String, String> param) {
        return mybatis.selectOne("Member.login", param);
    }

    public MemberDTO getMemberInfoById(String targetId) {
        return mybatis.selectOne("Member.findById", targetId);
    }
    //endregion

    //region delete
    public void withdraw(String targetId) {
        mybatis.delete("Member.delete", targetId);
    }
    //endregion

    //region update
    public void updateMemberInfo(MemberDTO modifiedInfo) {
        mybatis.update("Member.patch", modifiedInfo);
    }
    //endregion
}
