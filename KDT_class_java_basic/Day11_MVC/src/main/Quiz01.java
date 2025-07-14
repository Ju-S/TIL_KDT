package main;

import classes.MovieDTO;
import models.MovieDAO;

import java.util.Scanner;

public class Quiz01 {
    static Scanner sc = new Scanner(System.in);
    static MovieDAO movieDAO = new MovieDAO();

    // MVC ( Model / View / Controller )
    // Model : 비즈니스 로직과 데이터 작업
    // View : 사용자가 보게 될 UI
    // Controller : 사용자의 요청에 따라 처리 될 코드의 흐름을 통제
    public static void main(String[] args) {
        String menuRetryMsg = "잘못된 입력입니다.\n다시 입력하세요.\n>> ";

        while (true) {
            System.out.println("<< Netflix 영화 관리 시스템 >>");
            System.out.println("1. 신규 영화 등록");
            System.out.println("2. 영화 목록 출력");
            System.out.println("0> 시스템 종료");
            System.out.print(">> ");
            int selectedMenu = inputToInt(menuRetryMsg);

            switch (selectedMenu) {
                case 1:
                    regMovie();  // 신규 영화 등록
                    System.out.println("영화 등록 성공.");
                    break;
                case 2:
                    printMovieList();  // 영화 목록 출력
                    break;
                case 0:
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("없는 번호입니다.\n다시 입력하세요.");
                    break;
            }
        }
    }

    // ---------시스템 기능 ---------

    // 영화 신규 등록
    public static void regMovie() {
        String regIdRetryMsg = "잘못된 입력입니다.\n다시 입력하세요.\n신규 영화 ID: ";
        String regTitle, regGenre;
        int regId;

        while (true) {
            // ID가 초기값이거나 중복된 ID인 경우 반복
            // ID가 중복되지 않은 경우 탈출
            System.out.print("신규 영화 ID: ");
            regId = inputToInt(regIdRetryMsg);
            if (regId < 0) {
                // id 값이 음수라면 false
                System.out.println("ID는 0보다 작을 수 없습니다.\n다시 입력하세요.");
            } else if (movieDAO.checkIdDuplicated(regId)) {
                System.out.println("중복된 ID 입니다.\n다시 입력하세요.");
            } else {
                break;
            }
        }

        System.out.print("신규 영화 제목: ");
        regTitle = sc.nextLine();

        System.out.print("신규 영화 장르: ");
        regGenre = sc.nextLine();

        movieDAO.addMovie(new MovieDTO(regId, regTitle, regGenre));
        // 영화 등록
    }

    // 영화 목록 출력
    // ID(identification)   제목  장르
    public static void printMovieList() {
        if (movieDAO.getMovies().isEmpty()) {
            // 첫번째 칸이 null 이라면 movies는 비어있는 배열 이므로
            System.out.println("============================");
            System.out.println("출력할 수 있는 영화 정보가 없습니다.\n신규 영화 등록 후 다시 시도해주세요.");
            System.out.println("============================");
        } else {
            // 영화가 하나이상 등록된 경우
            System.out.println("============================");
            System.out.println("ID\t제목\t장르");
            System.out.println("============================");
            // 등록된 영화의 수 만큼 정보 출력
            for (MovieDTO movie : movieDAO.getMovies()) {
                System.out.println(movie.getId() + "\t" + movie.getTitle() + "\t" + movie.getGenre());
            }
            System.out.println("============================");
        }
    }

    // --------입력 예외 처리--------
    public static int inputToInt(String retryMsg) {
        // retryMsg : 입력에 실패했을 경우 출력할 메세지.
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.print(retryMsg);
            }
        }
    }
}
