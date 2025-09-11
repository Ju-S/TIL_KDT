package com.kedu.dao;

import com.kedu.dto.ContactsDTO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.AutoPopulatingList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactsDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void insert(ContactsDTO dto) throws Exception {
        String sql = "INSERT INTO contacts(id, name, contacts) VALUES(contact_seq.nextval, ?, ?)";
        jdbc.update(sql, dto.getName(), dto.getContact());
    }

    public List<ContactsDTO> getList() throws Exception {
        String sql = "SELECT * FROM contacts ORDER BY registry_date DESC";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(ContactsDTO.class));
    }
}
