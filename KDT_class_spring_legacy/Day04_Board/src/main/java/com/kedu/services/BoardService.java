package com.kedu.services;

import com.kedu.dao.BoardDAO;
import com.kedu.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;

    public List<BoardDTO> getList(String search, String searchOpt, int page) {
        int itemPerPage = 10;

        Map<String, Object> param = new HashMap<>();

        param.put("start", page * itemPerPage - (itemPerPage - 1));
        param.put("end", page * itemPerPage);

        if (search != null) {
            if (!search.isEmpty()) {
                param.put("searchOpt", searchOpt);
                param.put("search", search);
            }
        }
        return boardDAO.getPostsPage(param);
    }

    public int getMaxPage(String search, String searchOpt) {
        Map<String, String> param = new HashMap<>();
        if (search != null) {
            if (!search.isEmpty()) {
                param.put("searchOpt", searchOpt);
                param.put("search", search);
            }
        }
        return boardDAO.getMaxPage(param,10);
    }

    public int posting(BoardDTO dto) {
        return boardDAO.posting(dto);
    }

    public void updatePostsViewCntById(int id) {
        boardDAO.updatePostsViewCntById(id);
    }

    public BoardDTO getPostById(int id) {
        return boardDAO.getPostById(id);
    }

    public void deletePostById(int id) {
        boardDAO.deletePostById(id);
    }

    public void updatePostById(BoardDTO dto) {
        boardDAO.updatePostById(dto);
    }
}
