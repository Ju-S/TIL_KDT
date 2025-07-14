package dao;

import dto.GoldDTO;
import dto.Membership;
import dto.SilverDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<Membership> members = new ArrayList<>();

    //회원 등록(true 라면 등록성공, 아니라면 실패)
    public boolean signup(Membership userInfo) {
        try {
            members.add(userInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //회원 삭제(id를 통한)(true 라면 삭제성공, 아니라면 실패)
    public boolean removeUser(int targetId) {
        int foundIndex = findUserIndexById(targetId);
        if (foundIndex != -1) {
            members.remove(foundIndex);
            return true;
        } else {
            return false;
        }
    }

    //회원 수정(id를 통한)
    //수정 회원 검색 -> 없다면 메뉴로 -> 있다면 dao.modifyUser()이기 때문에 따로 검사를 하지 않는다.
    public void modifyUser(Membership modifiedInfo, int targetIndex) {
        members.set(targetIndex, modifiedInfo);
    }

    //모든 회원 목록 return
    public List<Membership> getAllUsers() {
        return members;
    }

    //특정 회원 목록 조회(검색식에 포함된 이름을 가진 silverDTO)(return null 이라면 조회된 목록 없음)
    public List<Membership> findUsersByName(String targetName) {
        List<Membership> resultUserList = new ArrayList<Membership>();  // 결과 저장용

        for (Membership member : members) {
            if (member.getName().contains(targetName)) {
                resultUserList.add(member);
            }
        }

        if (!resultUserList.isEmpty()) {
            return resultUserList;
        } else {
            return null;
        }
    }

    //특정 회원 index 조회(id가 일치하는)(return -1 이라면 조회된 목록 없음)
    public int findUserIndexById(int targetId) {
        for (Membership member : members) {
            if (member.getId() == targetId) {
                return members.indexOf(member);
            }
        }
        return -1;
    }

    //id중복 확인(true 라면 중복 되었다는 의미)
    public boolean isIdDuplicated(int id) {
        return findUserIndexById(id) != -1;
    }

    //회원 목록 비어있는지 확인(비어있다면 true, 아니라면 false)
    public boolean isEmpty() {
        return members.isEmpty();
    }
}
