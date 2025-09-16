package com.kedu.services;

import com.kedu.dao.FilesDAO;
import com.kedu.dto.FilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    @Autowired
    private FilesDAO filesDAO;

    public void upload(FilesDTO dto) {
        filesDAO.insert(dto);
    }

    public List<FilesDTO> list(int parentSeq) {
        return filesDAO.getListByParentSeq(parentSeq);
    }
}
