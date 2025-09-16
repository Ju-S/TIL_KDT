package com.kedu.dao;

import com.kedu.dto.BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    //region insert
    public int posting(BoardDTO postInfo) {
        mybatis.insert("Board.insert", postInfo);
        return postInfo.getId();
    }
    //endregion

    //region select
    public List<BoardDTO> getPostsPage(Map<String, Object> param) {
        return mybatis.selectList("Board.getPostList", param);
    }

    public int getMaxPage(Map<String, String> param, int itemPerPage) {
        Integer itemCount = mybatis.selectOne("Board.getMaxPage", param);

        if(itemCount != null) {
            return (itemCount - 1) / itemPerPage + 1;
        } else {
            return 0;
        }
    }

    public BoardDTO getPostById(int target) {
        return mybatis.selectOne("Board.selectById", target);
    }
    //endregion

    //region delete
    public void deletePostById(int target) {
        mybatis.delete("Board.delete", target);
    }
    //endregion

    //region update
    public void updatePostById(BoardDTO target) {
        mybatis.update("Board.updatePost", target);
    }

    public void updatePostsViewCntById(int target) {
        mybatis.update("Board.updateViewCount", target);
    }
    //endregion
}
