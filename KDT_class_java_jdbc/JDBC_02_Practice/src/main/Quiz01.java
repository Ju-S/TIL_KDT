package main;

import classes.MovieDTO;
import models.MovieDAO;

import java.util.List;
import java.util.Scanner;

public class Quiz01 {
    static Scanner sc = new Scanner(System.in);
    static MovieDAO movieDAO = new MovieDAO();

    // MVC ( Model / View / Controller )
    // Model : 비즈니스 로직과 데이터 작업
    // View : 사용자가 보게 될 UI
    // Controller : 사용자의 요청에 따라 처리 될 코드의 흐름을 통제
    public static void main(String[] args) {
        while (true) {
            System.out.println("<< Netflix 영화 관리 시스템 >>");
            System.out.println("1. 신규 영화 등록");
            System.out.println("2. 영화 목록 출력");
            System.out.println("3. 영화 검색");
            System.out.println("4. 영화 수정");
            System.out.println("5. 영화 삭제");
            System.out.println("0> 시스템 종료");
            int selectedMenu = inputToInt(">> ");

            try {
                switch (selectedMenu) {
                    case 1 -> regMovie();  // 신규 영화 등록
                    case 2 -> printMovieList(movieDAO.getAllMovies());  // 영화 목록 출력
                    case 3 -> searchMovies(); // 검색
                    case 4 -> updateMovie(); // 수정
                    case 5 -> removeMovie(); // 삭제
                    case 0 -> {
                        System.out.println("시스템을 종료합니다.");
                        System.exit(0);
                    }
                    default -> System.out.println("없는 번호입니다.\n다시 입력하세요.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ---------시스템 기능 ---------

    // 영화 신규 등록
    public static void regMovie() {
        try {
            movieDAO.addMovie(inputMovieInfo());
            System.out.println("영화 등록 성공.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 영화 등록
    }

    public static void updateMovie() {
        try {
            printMovieList(movieDAO.getAllMovies());
            int targetId = inputToInt("수정할 영화 ID: ");
            if (movieDAO.findMovieByID(targetId) != null) {
                MovieDTO modifiedMovie = inputMovieInfo();
                modifiedMovie.setId(targetId);
                movieDAO.updateMovie(modifiedMovie);
                System.out.println("수정 성공..");
            }
            System.out.println("수정 실패..");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("수정 실패..");
        }
    }

    public static MovieDTO inputMovieInfo() {
        System.out.print("신규 영화 제목: ");
        String title = sc.nextLine();

        System.out.print("신규 영화 장르: ");
        String genre = sc.nextLine();

        return new MovieDTO(0, title, genre);
    }

    public static void removeMovie() {
        try {
            printMovieList(movieDAO.getAllMovies());
            int targetId = inputToInt("삭제할 영화 ID: ");
            if (movieDAO.findMovieByID(targetId) != null) {
                movieDAO.deleteMovieById(targetId);
                System.out.println("삭제 성공..");
            }
            System.out.println("삭제 실패..");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("삭제 실패..");
        }
    }

    public static void searchMovies() throws Exception {
        outer:
        while (true) {
            System.out.println("1. 제목으로 검색");
            System.out.println("2. 장르로 검색");
            switch (inputToInt(">> ")) {
                case 1:
                    System.out.print("검색할 제목: ");
                    printMovieList(movieDAO.findMoviesByTitle(sc.nextLine()));
                    break outer;
                case 2:
                    System.out.print("검색할 장르: ");
                    printMovieList(movieDAO.findMoviesByGenre(sc.nextLine()));
                    break outer;
                default:
                    System.out.println("없는 메뉴입니다.\n다시 선택하세요.");
            }
        }
    }

    // 영화 목록 출력
    // ID(identification)   제목  장르
    public static void printMovieList(List<MovieDTO> movies) {
        if (movies.isEmpty()) {
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
            for (MovieDTO movie : movies) {
                System.out.println(movie.getId() + "\t" + movie.getTitle() + "\t" + movie.getGenre());
            }
            System.out.println("============================");
        }
    }

    // --------입력 예외 처리--------
    public static int inputToInt(String retryMsg) {
        while (true) {
            try {
                System.out.print(retryMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}
