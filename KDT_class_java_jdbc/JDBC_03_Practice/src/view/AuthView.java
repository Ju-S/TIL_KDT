package view;

import dao.MemberDAO;
import dto.MemberDTO;
import utils.InputUtil;

public class AuthView {
    private MemberDAO memberDAO = new MemberDAO();

    public boolean authSystem() {
        while(true) {
            System.out.println("<< 인증 시스템 >>");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("0. 종료");
            int selectedMenu = InputUtil.inputToInt(">> ");

            try {
                switch (selectedMenu) {
                    case 1 -> login();
                    case 2 -> register();
                    case 0 -> System.exit(0);
                    default -> System.out.println("없는 메뉴입니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void login() throws Exception {
        String id = InputUtil.inputToString("ID: ");
        String password = InputUtil.inputToString("PW: ");
        String loginName = memberDAO.loginMember(new MemberDTO(id, password));
        if(!loginName.isEmpty()) {
            System.out.println(loginName + " 님 로그인 성공");
        } else {
            System.out.println("ID 또는 PW가 일치하지 않습니다.");
        }
    }

    private void register() throws Exception {
        String id = "";
        while(id.isEmpty()) {
            id = InputUtil.inputToString("ID: ");
            if (memberDAO.isIDExist(id)) {
                System.out.println("해당 ID는 이미 존재합니다.");
                id = "";
            }
        }

        String password = InputUtil.inputToString("PW: ");
        String name = InputUtil.inputToString("Name: ");

        if(memberDAO.registerMember(new MemberDTO(id, password, name)) > 0) {
            System.out.println("회원가입 성공..");
        } else {
            System.out.println("회원가입 실패..");
        }
    }
}
