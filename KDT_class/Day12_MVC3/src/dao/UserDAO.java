package dao;

import dto.SilverDTO;

public class UserDAO {
    SilverDTO[] silvers = new SilverDTO[10];

    //회원 등록(true 라면 등록성공, 아니라면 실패)
    public boolean signup(SilverDTO silverUser) {
        for(int i = 0; i < silvers.length; i++) {
            if(silvers[i] == null) {
                silvers[i] = silverUser;
                return true;
            }
        }
        return false;
    }

    //회원 삭제(id를 통한)(true 라면 삭제성공, 아니라면 실패)
    public boolean removeUser(int targetId) {
        int foundUserIndex = findUserIndexById(targetId);
        if(foundUserIndex != -1) {
            silvers[foundUserIndex] = null; // 삭제처리
            return true;
        }
        return false;
    }

    //회원 수정(id를 통한)
    //수정 회원 검색 -> 없다면 메뉴로 -> 있다면 dao.modifyUser()이기 때문에 따로 검사를 하지 않는다.
    public void modifyUser(SilverDTO modifiedInfo, int targetIndex) {
        silvers[targetIndex] = modifiedInfo;
    }

    //모든 회원 목록 return
    public SilverDTO[] getAllUsers() {
        return silvers;
    }

    //특정 회원 목록 조회(검색식에 포함된 이름을 가진 silverDTO)(return null 이라면 조회된 목록 없음)
    public SilverDTO[] findUsersByName(String targetName) {
        SilverDTO[] resultUserList = new SilverDTO[silvers.length];  // 결과 저장용
        boolean isFound = false;  // 결과를 하나라도 저장이 되었다면 true

        for(int i = 0; i < silvers.length; i++) {
            if(silvers[i] != null && silvers[i].getName().contains(targetName)) {
                resultUserList[i] = silvers[i];
                isFound = true;
            }
        }

        if(isFound) {
            return resultUserList;
        } else {
            return null;
        }
    }

    //특정 회원 index 조회(id가 일치하는)(return -1 이라면 조회된 목록 없음)
    public int findUserIndexById(int targetId) {
        for(int i = 0; i < silvers.length; i++) {
            if(silvers[i] != null && silvers[i].getId() == targetId) {
                return i;
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
        for(int i = 0; i < silvers.length; i++) {
            if(silvers[i] != null) {
                return false;
            }
        }
        return true;
    }
}
