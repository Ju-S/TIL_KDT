package com.kedu.dao;

import com.kedu.dto.MessagesDTO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class MessagesDAO {

    @Autowired
    private BasicDataSource bds;

    public void insert(MessagesDTO dto) throws Exception {
        String sql = "INSERT INTO messages(id, writer, contents) VALUES(messages_seq.nextval, ?, ?)";

        try(Connection con = bds.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, dto.getWriter());
            pstat.setString(2, dto.getContents());
            pstat.executeUpdate();
        }
    }
}
