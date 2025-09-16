package com.kedu.dao;

import com.kedu.dto.FilesDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilesDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    public void insert(FilesDTO dto) {
        mybatis.insert("File.insert", dto);
    }

    public List<FilesDTO> getListByParentSeq(int parentSeq) {
        return mybatis.selectList("File.getListByParentSeq", parentSeq);
    }
}
