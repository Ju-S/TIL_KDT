package client.view;

import client.session.LoginInfo;
import client.utils.InputUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PostView {

    private DataInputStream dis;
    private DataOutputStream dos;

    public LoginInfo postSystem(DataInputStream dis, DataOutputStream dos, LoginInfo loginInfo) throws Exception {
        this.dis = dis;
        this.dos = dos;

        System.out.println("<< 온라인 방명록 >>");
        System.out.println("1. 방명록 남기기");
        System.out.println("2. 방명록 보기");
        System.out.println("3. 방명록 검색");
        System.out.println("4. 방명록 수정");
        System.out.println("5. 방명록 삭제");
        System.out.println("0. 로그아웃");
        int selectedMenu = InputUtil.inputToInt(">> ");
        dos.writeInt(selectedMenu);
        dos.flush();

        switch (selectedMenu) {
            case 1 -> posting(loginInfo);
            case 2 -> printPostsInfo();
            case 3 -> findPostByAuthor();
            case 4 -> modifiyingContents(loginInfo);
            case 5 -> removePost(loginInfo);
            case 0 -> {
                System.out.println("로그아웃 하였습니다.");
                return new LoginInfo("", "", false);
            }
            default -> System.out.println("없는 메뉴입니다.");
        }
        return loginInfo;
    }

    private void modifiyingContents(LoginInfo loginInfo) throws Exception {
        dos.writeUTF(loginInfo.getId());
        printPostsInfo();

        int targetId = InputUtil.inputToInt("수정할 방명록의 ID를 입력하세요: ");
        dos.writeInt(targetId);

        if(dis.readBoolean()) {
            String originalContents = dis.readUTF();
            System.out.println("기존 내용: " + originalContents);
            System.out.println("수정 내용이 비어있는 경우, 기존 내용으로 유지됩니다.");
            String modifiedContents = InputUtil.inputToString("수정 내용: ");

            dos.writeUTF(modifiedContents.isEmpty() ? originalContents : modifiedContents);
            if(dis.readBoolean()) {
                System.out.println("방명록 수정 성공.");
            } else {
                System.out.println("방명록 수정 실패.");
            }
        } else {
            System.out.println("방명록 수정 실패.");
        }
    }

    private void removePost(LoginInfo loginInfo) throws Exception {
        dos.writeUTF(loginInfo.getId());
        printPostsInfo();

        int targetId = InputUtil.inputToInt("삭제할 방명록의 ID를 입력하세요: ");
        dos.writeInt(targetId);

        if(dis.readBoolean()) {
            System.out.println("방명록 삭제 성공.");
        } else {
            System.out.println("방명록 삭제 실패.");
        }
    }

    private void findPostByAuthor() throws Exception {
        String keyword = InputUtil.inputToString("검색할 작성자 입력: ");

        dos.writeUTF(keyword);
        dos.flush();

        printPostsInfo();
    }

    private void posting(LoginInfo loginInfo) throws Exception {
        System.out.println(loginInfo.getName() + " 님 방명록에 남길 글을 작성해주세요.");
        String content = InputUtil.inputToString(">> ");

        dos.writeUTF(content);
        dos.writeUTF(loginInfo.getName());
        dos.writeUTF(loginInfo.getId());
        dos.flush();
    }

    private void printPostsInfo() throws Exception {
        int size = dis.readInt();
        if(size == 0) {
            System.out.println("출력 할 목록이 없습니다.");
        } else {
            System.out.println("글번호\t글내용\t작성자\t작성시간");
            for(int i = 0; i < size; i++) {
                System.out.print(dis.readInt() + "\t");
                System.out.print(dis.readUTF() + "\t");
                System.out.print(dis.readUTF() + "\t");
                System.out.println(dis.readUTF());
            }
        }
    }
}
