package main;

import dao.MenuDAO;
import dao.UserDAO;
import main.view.AuthView;
import main.view.CafeMenuView;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        MenuDAO menuDAO = new MenuDAO();

        AuthView authView = new AuthView(userDAO);
        CafeMenuView cafeMenuView = new CafeMenuView(menuDAO);

        boolean loginStatus = false;

        while(true) {
            if(!loginStatus) {
                loginStatus = authView.printView();
            } else {
                loginStatus = cafeMenuView.printView();
            }
        }
    }

    // 입력처리
    public static int inputToInt(String inputMsg) {
        while (true){
            try {
                System.out.print(inputMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    public static String inputToString(){
        return sc.nextLine();
    }

    public static void printDash() {
        System.out.println("=========================================");
    }
}
