package client.view;

import client.classes.LoginInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class AuthSystemView {
    public LoginInfo authSystem(Scanner sc, DataInputStream dis, DataOutputStream dos) throws Exception {
        System.out.println("<< 인증 >>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 시스템 종료");
        System.out.print(">> ");
        int selectedMenu = Integer.parseInt(sc.nextLine());
        dos.writeInt(selectedMenu);
        dos.flush();

        switch (selectedMenu) {
            case 1: {
                System.out.print("ID: ");
                String id = sc.nextLine();

                System.out.print("PW: ");
                String password = sc.nextLine();

                dos.writeUTF(id);
                dos.writeUTF(password);
                dos.flush();

                String name = dis.readUTF();
                if (!name.isEmpty()) { // 로그인 성공 시
                    System.out.println("로그인 성공.");
                    return new LoginInfo(id, name, true);
                } else {
                    System.out.println("로그인 실패.");
                }
                break;
            }
            case 2: {
                String id = "";
                while (true) {
                    System.out.print("ID: ");
                    id = sc.nextLine();
                    dos.writeUTF(id);
                    dos.flush(); // 서버에 중복 확인 요청
                    if (dis.readBoolean()) {  // true 라면 id 중복
                        System.out.println("중복된 ID가 있습니다.\n다시 입력해주세요.");
                    } else {
                        break;
                    }
                }

                System.out.print("PW: ");
                String password = sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                dos.writeUTF(password);
                dos.writeUTF(name);
                dos.flush();

                if(dis.readBoolean()) {
                    System.out.println("회원가입이 완료되었습니다.");
                } else {
                    System.out.println("회원가입실패.");
                }
                break;
            }
            case 0:
                System.exit(0);
            default:
                System.out.println("없는 메뉴입니다.");
                break;
        }
        // 로그인 안되어있는 상태
        return new LoginInfo("", "", false);
    }
}
