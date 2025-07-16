package client.utils;

import java.util.Scanner;

public final class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static int inputToInt(String retryMsg) {
        while(true) {
            try {
                System.out.print(retryMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    public static String inputToString(String retryMsg) {
        while(true) {
            try {
                System.out.print(retryMsg);
                return sc.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("문자로 입력해주세요.");
            }
        }
    }
}
