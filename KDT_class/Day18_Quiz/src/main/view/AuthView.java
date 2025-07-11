package main.view;

import dao.UserDAO;
import main.Main;

public class AuthView {
    UserDAO userDAO;

    public AuthView(){}
    public AuthView(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void printView() {
        System.out.println("<<< 인증 >>>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 시스템 종료");

        switch(Main.inputToInt(">> ")) {
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            case 0:
                System.out.println("시스템을 종료합니다...");
                System.exit(0);
            default:
                System.out.println("없는 메뉴 입니다.");
                break;
        }
    }

    public void signIn() {
        System.out.print("ID: ");
        String id = Main.inputToString();

        System.out.print("PW: ");
        String password = Main.inputToString();

        if(userDAO.signIn(id, password)){
            System.out.println("로그인 성공...");
        } else {
            System.out.println("아이디 또는 비밀번호가 맞지 않습니다.");
        }
        userDAO.signIn(id, password);
        Main.loginStatus = true;
    }

    public void signUp() {
        String id = "";
        while(id.isEmpty()) {
            System.out.print("ID: ");
            id = Main.inputToString();
            if(userDAO.isDuplicatedId(id)) {
                id = "";
                System.out.println("중복된 아이디 입니다.");
            }
        }

        System.out.print("PW: ");
        String password = Main.inputToString();

        userDAO.signUp(id, password);
        System.out.println("회원가입 성공...");
    }
}
