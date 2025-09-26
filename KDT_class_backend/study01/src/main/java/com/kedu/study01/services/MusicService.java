package com.kedu.study01.services;

import com.kedu.study01.dao.MusicDAO;
import com.kedu.study01.dto.MusicDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicDAO musicDAO;

    public List<MusicDTO> selectAll() {
        return musicDAO.selectAll();
    }

    public void insert(MusicDTO dto) {
        musicDAO.insert(dto);
    }

    public void update(MusicDTO dto) {
        musicDAO.update(dto);
    }

    public void delete(int targetId) {
        musicDAO.delete(targetId);
    }
}
