package Main;

import DAO.MovieDAO;
import DTO.MovieDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MovieDAO movieDAO = new MovieDAO();

        while (true) {
            System.out.println("<< Netflix 영화 관리 시스템 >>");
            System.out.println("1. 신규 영화 등록");
            System.out.println("2. 영화 목록 출력");
            System.out.println("3. 영화 검색(제목 or 장르)");
            System.out.println("4. 영화 수정(ID)");
            System.out.println("5. 영화 삭제(ID)");
            System.out.println("0. 시스템 종료");

            int selectedMenu = inputToInt(sc, ">> ");

            switch (selectedMenu) {
                case 1:
                    printDash();
                    movieDAO.regMovie(inputMovieInfo(sc));
                    System.out.println("등록이 완료되었습니다.");
                    printDash();
                    break;
                case 2:
                    printMovieList(movieDAO.getMovieList());
                    break;
                case 3:
                    printDash();
                    printMovieList(searchSwitch(sc, movieDAO));
                    break;
                case 4:
                    if(!printMovieList(movieDAO.getMovieList())){
                        break;
                    }
                    MovieDTO modifiedMovieInfo = modifyMovieInfo(sc, movieDAO, inputToInt(sc, "수정할 영화의 ID를 입력하세요: "));
                    if(modifiedMovieInfo != null) {
                        movieDAO.modifyMovieInfo(modifiedMovieInfo);
                        System.out.println("수정이 완료되었습니다.");
                    } else {
                        System.out.println("검색된 영화가 없습니다.");
                    }
                    printDash();
                    break;
                case 5:
                    if(!printMovieList(movieDAO.getMovieList())){
                        break;
                    }
                    if(movieDAO.removeMovie(inputToInt(sc, "삭제할 영화의 ID를 입력하세요."))) {
                        System.out.println("삭제가 완료되었습니다.");
                    } else {
                        System.out.println("검색된 영화가 없습니다.");
                    }
                    printDash();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("메뉴에 없는 번호입니다.\n다시 입력해주세요.");
                    break;
            }
        }
    }

    //----------입력----------
    // 등록할 MovieDTO의 정보 입력받기
    public static MovieDTO inputMovieInfo(Scanner sc) {
        System.out.print("영화 제목: ");
        String title = sc.nextLine();
        System.out.print("영화 장르: ");
        String genre = sc.nextLine();

        Date publishDate;
        while(true) {
            try {
                System.out.print("영화 출시일(yy/MM/dd): ");
                publishDate = stringToDate(sc.nextLine());
                break;
            } catch(Exception e) {
                //e.printStackTrace();
                System.out.println("지정된 형식으로 다시 입력해주세요.");
            }
        }

        return new MovieDTO(title, genre, publishDate);
    }

    // 수정할 MovieDTO의 정보 입력받기
    public static MovieDTO modifyMovieInfo(Scanner sc, MovieDAO movieDAO, int targetId) {
        MovieDTO targetMovie = movieDAO.findMovieById(targetId);

        if(targetMovie == null){
            return null;
        }

        System.out.println("수정할 영화 정보 입력(공백입력시, 기존 정보 유지");

        System.out.print("영화 제목(현재: "+ targetMovie.getTitle() +"): ");
        String title = sc.nextLine();
        if(title.isEmpty()) {
            title = targetMovie.getTitle();
        }

        System.out.print("영화 장르(현재: "+ targetMovie.getGenre() +"): ");
        String genre = sc.nextLine();
        if(genre.isEmpty()) {
            genre = targetMovie.getGenre();
        }

        Date publishDate;
        while(true) {
            try {
                System.out.print("영화 출시일(yy/MM/dd)(현재: "+ dateToString(targetMovie.getPublishDate(), "yy/MM/dd") +"): ");
                String dateStr = sc.nextLine();
                if(dateStr.isEmpty()) {
                    publishDate = targetMovie.getPublishDate();
                    break;
                }
                publishDate = stringToDate(dateStr);
                break;
            } catch(Exception e) {
                //e.printStackTrace();
                System.out.println("지정된 형식으로 다시 입력해주세요.");
            }
        }

        return new MovieDTO(targetId, title, genre, publishDate);
    }

    // 숫자 입력 예외 처리(inputMsg: 입력 콘솔 안내 문구)
    public static int inputToInt(Scanner sc, String inputMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }
    //-----------------------

    //------------출력-------------
    // 파라미터로 받은 movieList의 정보 출력(false 반환시, 출력한 값이 없음.)
    public static boolean printMovieList(List<MovieDTO> movieList) {
        printDash();
        if(!movieList.isEmpty()) {
            System.out.println("ID\tTitle\tGenre\tpub_date");
            for (MovieDTO movie : movieList) {
                System.out.println(movie.getId() + "\t" +
                        movie.getTitle() + "\t" +
                        movie.getGenre() + "\t" +
                        dateToString(movie.getPublishDate(), "yyyy.MM"));
            }
            printDash();
            return true;
        } else {
            System.out.println("출력할 영화 목록이 없습니다.");
            printDash();
            return false;
        }
    }

    public static void printDash() {
        System.out.println("==================================");
    }
    //----------------------------

    //------------검색------------
    public static List<MovieDTO> searchSwitch(Scanner sc, MovieDAO movieDAO) {
        while(true) {
            int searchTitleOrGenre = inputToInt(sc, "1. 제목\n2. 장르\n>> ");
            System.out.print("검색할 키워드: ");
            String searchStr = sc.nextLine();
            switch (searchTitleOrGenre) {
                case 1:
                    return movieDAO.findMoviesByTitle(searchStr);
                case 2:
                    return movieDAO.findMoviesByGenre(searchStr);
                default:
                    System.out.println("메뉴에 없는 번호입니다.\n다시 입력해주세요.");
                    break;
            }
        }
    }
    //---------------------------

    //-------------utils-------------
    // String -> Date 날짜 변환
    public static Date stringToDate(String dateStr) throws Exception {
        SimpleDateFormat parseSdf = new SimpleDateFormat("yy/MM/dd");
        return parseSdf.parse(dateStr);
    }

    // Date -> String 날짜 변환
    public static String dateToString(Date date, String format) {
        SimpleDateFormat formatSdf = new SimpleDateFormat(format);
        return formatSdf.format(date);
    }
    //--------------------------------
}
