package dao;

import dto.MenuDTO;

import java.util.HashMap;
import java.util.Map;

public class MenuDAO {
    private int id = 1001;

    private Map<Integer, MenuDTO> menuList = new HashMap<>();

    // 메뉴 등록
    public void addMenu(MenuDTO newMenu) {
        menuList.put(id++, newMenu);
    }

    // 메뉴 목록 출력
    public Map<Integer, MenuDTO> getMenuList() {
        return menuList;
    }

    // 메뉴 검색(이름)
    public Map<Integer, MenuDTO> findMenuByName(String name) {
        Map<Integer, MenuDTO> tempMenu = new HashMap<>();
        for(int key : menuList.keySet()) {
            if(menuList.get(key).getName().contains(name)) {
                tempMenu.put(key, menuList.get(key));
            }
        }
        return tempMenu;
    }

    // 메뉴 수정(id로)
    public void modifyingMenu(int id, MenuDTO modifiedMenu) {
        menuList.put(id, modifiedMenu);
    }

    // 메뉴 삭제(id로)
    public void removeMenu(int id) {
        menuList.remove(id);
    }

    public boolean isExistMenu(int id) {
        return menuList.containsKey(id);
    }
}
