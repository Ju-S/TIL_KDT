package server.dao;

import server.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    List<UserDTO> users = new ArrayList<>();

    // 회원가입
    public void regUser(UserDTO userInfo) {
        users.add(userInfo);
    }

    // 로그인(성공 시, name 반환. 실패 시, null 반환)
    public String login(String id, String password) {
        for(UserDTO user : users) {
            if(user.getId().equals(id) && user.getPassword().equals(password)) {
                return user.getName();
            }
        }
        return "";
    }

    // id 중복 확인(중복되었다면 true 아니라면 false)
    public boolean isDuplicatedId(String id) {
        for(UserDTO user : users) {
            if(user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
