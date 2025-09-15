package com.kedu.dao;

import com.kedu.dto.FilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FilesDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void insert(FilesDTO dto) {
        String sql = "INSERT INTO files(seq, writer, ori_name, sys_name, parent_seq) VALUES(files_seq.nextval, ?, ?, ?, ?)";
        jdbc.update(sql, dto.getWriter(), dto.getOriName(), dto.getSysName(), dto.getParentSeq());
    }
}
