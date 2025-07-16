package server.thread;

import server.dao.MemberDAO;
import server.dto.MemberDTO;
import server.security.CustomEncrypt;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class AuthWorker extends Thread {

    private MemberDAO memberDAO;

    private DataInputStream dis;
    private DataOutputStream dos;

    public AuthWorker() {
    }

    public AuthWorker(DataInputStream dis, DataOutputStream dos, MemberDAO memberDAO) {
        this.memberDAO = memberDAO;

        this.dis = dis;
        this.dos = dos;
    }

    public void processAuth() throws Exception {
        int userSelectedMenu = dis.readInt();
        System.out.println(userSelectedMenu);

        switch (userSelectedMenu) {
            case 1 -> login(); // 로그인
            case 2 -> register(); // 회원가입
        }
    }

    private void login() throws Exception {
        String id = dis.readUTF();
        String password = CustomEncrypt.encrypt(dis.readUTF());
        System.out.println(id + password);

        String name = memberDAO.loginMember(new MemberDTO(id, password));
        dos.writeUTF(name);
        dos.flush();
        System.out.println(name);
    }

    private void register() throws Exception {
        String id = null;
        boolean isDuplicatedId = true;
        while (isDuplicatedId) {
            id = dis.readUTF();
            isDuplicatedId = memberDAO.isIDExist(id);
            dos.writeBoolean(isDuplicatedId);
            dos.flush();
        }
        System.out.println(isDuplicatedId);

        String password = CustomEncrypt.encrypt(dis.readUTF());
        String name = dis.readUTF();
        memberDAO.registerMember(new MemberDTO(id, password, name));

        //회원가입 성공 상태 반환
        dos.writeBoolean(true);
        dos.flush();
    }
}
