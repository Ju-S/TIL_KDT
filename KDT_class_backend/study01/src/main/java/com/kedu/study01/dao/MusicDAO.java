package com.kedu.study01.dao;

import com.kedu.study01.dto.MusicDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicDAO {

    private final SqlSession mybatis;

    public List<MusicDTO> findByTitle(String searchQuery) {
        return mybatis.selectList("Music.findByTitle", searchQuery);
    }

    public List<MusicDTO> selectAll() {
        return mybatis.selectList("Music.selectAll");
    }

    public void insert(MusicDTO dto) {
        mybatis.insert("Music.insert", dto);
    }

    public void update(MusicDTO dto) {
        mybatis.update("Music.update", dto);
    }

    public void delete(int targetId) {
        mybatis.delete("Music.delete", targetId);
    }
}
