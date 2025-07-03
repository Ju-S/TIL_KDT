package client.view;

import client.classes.LoginInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class PostSystemView {
    public LoginInfo postSystem(Scanner sc, DataInputStream dis, DataOutputStream dos, LoginInfo loginInfo) throws Exception {
        System.out.println("<< 온라인 방명록 >>");
        System.out.println("1. 방명록 남기기");
        System.out.println("2. 방명록 보기");
        System.out.println("3. 방명록 검색");
        System.out.println("4. 방명록 삭제");
        System.out.println("0. 로그아웃");
        System.out.print(">> ");
        int selectedMenu = Integer.parseInt(sc.nextLine());
        dos.writeInt(selectedMenu);
        dos.flush();

        switch (selectedMenu) {
            case 1:
                System.out.println(loginInfo.getName() + " 님 방명록에 남길 글을 작성해주세요.");
                System.out.print(">> ");
                String content = sc.nextLine();

                dos.writeUTF(content);
                dos.writeUTF(loginInfo.getName());
                dos.writeUTF(loginInfo.getId());
                dos.flush();

                break;
            case 2:
                printPostsInfo(dis);
                break;
            case 3:
                System.out.print("검색할 작성자 입력: ");
                String keyword = sc.nextLine();

                dos.writeUTF(keyword);
                dos.flush();

                printPostsInfo(dis);
                break;
            case 4:
                dos.writeUTF(loginInfo.getId());
                printPostsInfo(dis);

                System.out.print("삭제할 방명록의 ID를 입력하세요: ");
                int targetId = Integer.parseInt(sc.nextLine());
                dos.writeInt(targetId);

                if(dis.readBoolean()) {
                    System.out.println("방명록 삭제 성공.");
                } else {
                    System.out.println("방명록 삭제 실패.");
                }
                break;
            case 0: // 로그아웃
                System.out.println("로그아웃 하였습니다.");
                return new LoginInfo("", "", false);
            default:
                System.out.println("없는 메뉴입니다.");
                break;
        }
        return loginInfo;
    }

    public void printPostsInfo(DataInputStream dis) throws Exception {


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
