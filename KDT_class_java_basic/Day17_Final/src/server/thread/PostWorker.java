package server.thread;

import server.dao.PostDAO;
import server.dto.PostDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

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
            case 1: { // 방명록 남기기
                String contents = dis.readUTF();
                String author = dis.readUTF();
                String authorId = dis.readUTF();
                postDAO.addContents(new PostDTO(contents, author, authorId));
                break;
            }
            case 2: { // 방명록 출력
                sendGuestBookInfo(postDAO.getPosts());
                break;
            }
            case 3: { // 방명록 검색(author로 검색)
                String author = dis.readUTF();
                sendGuestBookInfo(postDAO.findContentsByAuthor(author));
                break;
            }
            case 4: { // 방명록 삭제
                String authorId = dis.readUTF();
                sendGuestBookInfo(postDAO.findContentsByAuthorId(authorId));

                // 다른 이용자의 Post 삭제하지 못하게 막기위한 사용자 Id 받기
                int targetId = dis.readInt();
                dos.writeBoolean(postDAO.deletePost(targetId, authorId));
                dos.flush();
                break;
            }
            default:
                break;
        }
    }


    public void sendGuestBookInfo(List<PostDTO> posts) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        dos.writeInt(posts.size());
        dos.flush();

        for (PostDTO post : posts) {
            dos.writeInt(post.getId());
            dos.writeUTF(post.getContents());
            dos.writeUTF(post.getAuthor());
            dos.writeUTF(sdf.format(post.getCreatedAt()));
        }
        dos.flush();
    }
}
