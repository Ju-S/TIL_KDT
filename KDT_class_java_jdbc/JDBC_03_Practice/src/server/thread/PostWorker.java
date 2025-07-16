package server.thread;

import server.dao.PostDAO;
import server.dto.PostDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PostWorker {

    PostDAO postDAO;

    DataInputStream dis;
    DataOutputStream dos;

    public PostWorker() {
    }

    public PostWorker(DataInputStream dis, DataOutputStream dos, PostDAO postDAO) {
        try {
            this.postDAO = postDAO;

            this.dis = dis;
            this.dos = dos;
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void processPost() throws Exception {
        int userSelectedMenu = dis.readInt();
        System.out.println(userSelectedMenu);

        switch (userSelectedMenu) {
            case 1 -> insertPost(); // 방명록 남기기
            case 2 -> sendGuestBookInfo(postDAO.getPosts()); // 방명록 출력
            case 3 -> searchPost(); // 방명록 검색(author로 검색)
            case 4 -> modifyingPost(); // 방명록 수정
            case 5 -> removePost(); // 방명록 삭제
        }
    }

    private void insertPost() throws Exception {
        String contents = dis.readUTF();
        String author = dis.readUTF();
        String authorId = dis.readUTF();
        postDAO.addContents(new PostDTO(contents, author, authorId));
    }

    private void searchPost() throws Exception {
        String author = dis.readUTF();
        sendGuestBookInfo(postDAO.findContentsByAuthor(author));
    }

    private void modifyingPost() throws Exception {
        String authorId = dis.readUTF();
        sendGuestBookInfo(postDAO.findContentsByAuthorId(authorId));

        // 다른 이용자의 Post 수정하지 못하게 막기위한 사용자 Id 받기
        int targetId = dis.readInt();
        if(postDAO.isOwnedPost(authorId, targetId)) {
            dos.writeBoolean(true);
            dos.writeUTF(postDAO.findContentsById(targetId).getContents());
            dos.flush();

            dos.writeBoolean(postDAO.modifiyngContents(dis.readUTF(), targetId));
        } else {
            dos.writeBoolean(false);
        }
        dos.flush();
    }

    private void removePost() throws Exception {
        String authorId = dis.readUTF();
        sendGuestBookInfo(postDAO.findContentsByAuthorId(authorId));

        // 다른 이용자의 Post 삭제하지 못하게 막기위한 사용자 Id 받기
        int targetId = dis.readInt();
        dos.writeBoolean(postDAO.deletePost(targetId, authorId));
        dos.flush();
    }


    private void sendGuestBookInfo(Map<Integer, PostDTO> posts) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        System.out.println("size: " + posts.size());
        dos.writeInt(posts.size());
        dos.flush();

        for (int key : posts.keySet()) {
            PostDTO post = posts.get(key);
            dos.writeInt(key);
            dos.writeUTF(post.getContents());
            dos.writeUTF(post.getAuthor());
            dos.writeUTF(sdf.format(post.getCreatedAt()));
            System.out.println(key + " send");
        }
        dos.flush();
    }
}
