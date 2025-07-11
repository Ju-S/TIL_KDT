package main.view;

import dao.MenuDAO;
import dto.MenuDTO;
import main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CafeMenuView {
    MenuDAO menuDAO;

    public CafeMenuView() {}
    public CafeMenuView(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public boolean printView() {
        System.out.println("<<< 카페 메뉴 관리 시스템 >>>");
        System.out.println("1. 신규 메뉴 등록");
        System.out.println("2. 메뉴 목록 출력");
        System.out.println("3. 메뉴 검색");
        System.out.println("4. 메뉴 수정");
        System.out.println("5. 메뉴 삭제");
        System.out.println("0. 로그아웃");

        switch(Main.inputToInt(">> ")) {
            case 1:
                return registerMenu();
            case 2:
                return printMenu(menuDAO.getMenuList());
            case 3:
                return printMenu(findMenuByName());
            case 4:
                printMenu(menuDAO.getMenuList());
                return modifyMenu(Main.inputToInt("수정할 메뉴의 ID를 입력하세요."));
            case 5:
                printMenu(menuDAO.getMenuList());
                return removeMenu(Main.inputToInt("삭제할 메뉴의 ID를 입력하세요."));
            case 0:
                System.out.println("로그아웃 하였습니다..");
                return false;
            default:
                System.out.println("없는 메뉴 입니다.");
                return true;
        }
    }

    public boolean printMenu(Map<Integer, MenuDTO> menuList) {
        Main.printDash();
        if(!menuList.isEmpty()) {
            System.out.println("ID\t\t이름\t\t\t가격\t\t출시일");
            Main.printDash();
            for (int key : menuList.keySet()) {
                System.out.println(key + "\t" +
                                    menuList.get(key).getName() + "\t" +
                                    menuList.get(key).getPrice() + "\t" +
                                    dateToString(menuList.get(key).getLaunchingDate()));
            }
        } else {
            System.out.println("등록된 메뉴가 없습니다.");
        }
        Main.printDash();
        return true;
    }

    public Map<Integer, MenuDTO> findMenuByName() {
        System.out.print("검색할 메뉴의 이름: ");
        String name = Main.inputToString();
        return menuDAO.findMenuByName(name);
    }

    public boolean removeMenu(int id) {
        if(menuDAO.isExistMenu(id)) {
            menuDAO.removeMenu(id);
            System.out.println("메뉴가 삭제되었습니다.");
        } else {
            System.out.println("없는 메뉴입니다.");
        }
        return true;
    }

    public boolean modifyMenu(int id) {
        if(menuDAO.isExistMenu(id)) {
            menuDAO.modifyingMenu(id, inputMenuDTO());
            System.out.println("메뉴가 수정되었습니다.");
        } else {
            System.out.println("없는 메뉴입니다.");
        }
        return true;
    }

    public boolean registerMenu() {
        menuDAO.addMenu(inputMenuDTO());
        return true;
    }

    private MenuDTO inputMenuDTO() {
        System.out.print("메뉴 이름: ");
        String name = Main.inputToString();

        int price = Main.inputToInt("메뉴 가격: ");

        Date launchingDate = inputLaunchingDate();

        return new MenuDTO(name, price, launchingDate);
    }

    private Date inputLaunchingDate() {
        while(true) {
            try {
                System.out.print("출시일 입력(yyyy/MM): ");
                String launchingDate = Main.inputToString();
                return stringToDate(launchingDate);
            } catch (Exception e) {
                System.out.println("형식에 맞춰 입력해주세요.");
            }
        }
    }

    private Date stringToDate(String input) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        return sdf.parse(input);
    }

    private String dateToString(Date input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
        return sdf.format(input);
    }
}
