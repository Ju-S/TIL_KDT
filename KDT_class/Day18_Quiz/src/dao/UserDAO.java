package dao;

import dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    Map<String, UserDTO> users = new HashMap<>();

    //회원가입
    public void signUp(String id, String password) {
        users.put(id, new UserDTO(password));
    }

    //로그인
    public boolean signIn(String id, String password) {
        if(users.containsKey(id)) {
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }

    //ID 중복체크
    public boolean isDuplicatedId(String id) {
        return users.containsKey(id);
    }
}
