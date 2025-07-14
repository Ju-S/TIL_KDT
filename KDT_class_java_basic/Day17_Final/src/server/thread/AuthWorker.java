package server.thread;

import server.dao.UserDAO;
import server.dto.UserDTO;
import server.CustomEncrypt;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class AuthWorker extends Thread {

    UserDAO userDAO;

    DataInputStream dis;
    DataOutputStream dos;

    public AuthWorker() {
    }

    public AuthWorker(DataInputStream dis, DataOutputStream dos, UserDAO userDAO) {
        this.userDAO = userDAO;

        this.dis = dis;
        this.dos = dos;
    }

    public void processAuth() throws Exception {
        int userSelectedMenu = dis.readInt();
        System.out.println(userSelectedMenu);

        switch (userSelectedMenu) {
            case 1: // 로그인
            {
                String id = dis.readUTF();
                String password = CustomEncrypt.encrypt(dis.readUTF());

                String name = userDAO.login(id, password);
                dos.writeUTF(name);
                dos.flush();
                break;
            }
            case 2: // 회원가입
            {
                String id = null;
                boolean isDuplicatedId = true;
                while (isDuplicatedId) {
                    id = dis.readUTF();
                    isDuplicatedId = userDAO.isDuplicatedId(id);
                    dos.writeBoolean(isDuplicatedId);
                    dos.flush();
                }
                System.out.println(isDuplicatedId);

                String password = CustomEncrypt.encrypt(dis.readUTF());
                String name = dis.readUTF();
                userDAO.regUser(new UserDTO(id, password, name));

                //회원가입 성공 상태 반환
                dos.writeBoolean(true);
                dos.flush();
                break;
            }
            default:
                break;
        }
    }
}
