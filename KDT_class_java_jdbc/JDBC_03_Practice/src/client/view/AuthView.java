package client.view;

import client.session.LoginInfo;
import server.dao.MemberDAO;
import server.dto.MemberDTO;
import client.utils.InputUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class AuthView {

    private DataInputStream dis;
    private DataOutputStream dos;

    public LoginInfo authSystem(DataInputStream dis, DataOutputStream dos) throws Exception {
        this.dis = dis;
        this.dos = dos;

        System.out.println("<< 인증 시스템 >>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 종료");
        int selectedMenu = InputUtil.inputToInt(">> ");

        dos.writeInt(selectedMenu);
        dos.flush();

        try {
            switch (selectedMenu) {
                case 1 -> { return login(); }
                case 2 -> register();
                case 0 -> System.exit(0);
                default -> System.out.println("없는 메뉴입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginInfo("", "", false);
    }

    private LoginInfo login() throws Exception {
        String id = InputUtil.inputToString("ID: ");
        String password = InputUtil.inputToString("PW: ");

        dos.writeUTF(id);
        dos.writeUTF(password);
        dos.flush();

        String loginName = dis.readUTF();
        if(!loginName.isEmpty()) {
            System.out.println(loginName + " 님 로그인 성공");
            return new LoginInfo(id, loginName, true);
        } else {
            System.out.println("ID 또는 PW가 일치하지 않습니다.");
            return new LoginInfo("", "", false);
        }
    }

    private void register() throws Exception {
        String id = "";
        while(id.isEmpty()) {
            id = InputUtil.inputToString("ID: ");
            dos.writeUTF(id);
            dos.flush();

            if (dis.readBoolean()) {
                System.out.println("해당 ID는 이미 존재합니다.");
                id = "";
            }
        }

        String password = InputUtil.inputToString("PW: ");
        String name = InputUtil.inputToString("Name: ");

        dos.writeUTF(password);
        dos.writeUTF(name);

        if(dis.readBoolean()) {
            System.out.println("회원가입 성공..");
        } else {
            System.out.println("회원가입 실패..");
        }
    }
}
