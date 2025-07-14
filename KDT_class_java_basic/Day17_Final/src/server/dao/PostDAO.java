package server.dao;

import server.dto.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private int postInitialId = 1001;
    private List<PostDTO> posts = new ArrayList<>();

    // 방명록 조회
    public List<PostDTO> getPosts() {
        return posts;
    }

    // 방명록 검색(author로 검색, author는 name 이다.)
    public List<PostDTO> findContentsByAuthor(String author) {
        List<PostDTO> resultPosts = new ArrayList<>();
        for (PostDTO post : posts) {
            if (post.getAuthor().contains(author)) {
                resultPosts.add(post);
            }
        }
        return resultPosts;
    }

    // 방명록 검색(authorId로 검색, author는 id 이다.)
    public List<PostDTO> findContentsByAuthorId(String authorId) {
        List<PostDTO> resultPosts = new ArrayList<>();
        for (PostDTO post : posts) {
            if (post.getAuthorId().equals(authorId)) {
                resultPosts.add(post);
            }
        }
        return resultPosts;
    }

    // 방명록 생성
    public void addContents(PostDTO post) {
        post.setId(postInitialId++);
        posts.add(post);
        System.out.println(postInitialId);
    }

    // 방명록 삭제(나의 name이 author로 등록된 글만 삭제 가능.)
    public boolean deletePost(int targetId, String loginUserId) {
        for (PostDTO post : findContentsByAuthorId(loginUserId)) {
            if (post.getId() == targetId) {
                posts.remove(post);
                return true;
            }
        }
        return false;
    }
}
