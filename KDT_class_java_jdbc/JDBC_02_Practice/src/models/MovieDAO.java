package models;

import classes.MovieDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DBMS에 동시 접속자가 100명 이상일 경우
// DBMS가 뻗는 현상 발생 가능성이 있음.
// DBCP (DataBase Connection Pool) : 미리 만들어둔 Connection 인스턴스를
// 접속자에게 대여하고 반환받는 매커니즘으로 DBMS에 도달하는 충격을 환화하는 버퍼 라이브러리

// DAO: (Data Access Object)
// 데이터의 CRUD를 담당
public class MovieDAO {

    private BasicDataSource bds = new BasicDataSource(); // DBCP 인스턴스 생성

    private static MovieDAO instance;

    // multi-thread 환경에서 shynchronized 메서드는 multi-thread 환경을 거부한다.
    // 한 thread 에서 synchronized 메서드에 접근하면 다른 thread 에서의 접근을 거부한다.
    public synchronized static MovieDAO getInstance() {
        if(instance == null) {
            instance = new MovieDAO();
        }
        return instance;
    }

    private MovieDAO() {
        bds.setUsername("kedu");
        bds.setPassword("kedu");
        bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        bds.setInitialSize(10);
    }

    private Connection getConnection() throws Exception {
        return bds.getConnection();
    }

//    private Connection getConnection() throws Exception {
//        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
//        String dbID = "kedu";
//        String dbPW = "kedu";
//        return DriverManager.getConnection(dbURL, dbID, dbPW);
//    }

    public void addMovie(MovieDTO movieDTO) throws Exception {
//        try(Connection con = this.getConnection();
//            Statement stat = con.createStatement()) {
//            String sql = "insert into movie values(movie_seq.nextval, '" + movieDTO.getTitle() + "', '" + movieDTO.getGenre() + "')";
//            stat.executeUpdate(sql);
//        }
        String sql = "insert into movie values(movie_seq.nextval, ?, ?)";
        try(Connection con = this.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, movieDTO.getTitle());
            stat.setString(2, movieDTO.getGenre());
            stat.executeUpdate();
        }
    }

    public void updateMovie(MovieDTO movieDTO) throws Exception {
        String sql = "update movie set title = ?, genre = ? where id = ?";
        try(Connection con = this.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
//            String sql = "update movie set title = '" + movieDTO.getTitle() +
//                                        "', genre = '" + movieDTO.getGenre() +
//                                    "' where id = " + movieDTO.getId();
            stat.setString(1, movieDTO.getTitle());
            stat.setString(2, movieDTO.getGenre());
            stat.setInt(3, movieDTO.getId());
            stat.executeUpdate();
        }
    }

    public void deleteMovieById(int targetId) throws Exception {
        String sql = "delete from movie where id = ?";
        try(Connection con = this.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setInt(1, targetId);
            stat.executeUpdate();
        }
    }

    public MovieDTO findMovieByID(int target) throws Exception {
        String sql = "select * from movie where id = ?";
        try(Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setInt(1, target);
            return getMovies(stat).getFirst();
        }
    }

    public List<MovieDTO> findMoviesByTitle(String target) throws Exception {
        String sql = "select * from movie where title like ?";
        try(Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "%" + target + "%");
            return getMovies(stat);
        }
    }

    public List<MovieDTO> findMoviesByGenre(String target) throws Exception {
        String sql = "select * from movie where genre like ?";
        try(Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "%" + target + "%");
            return getMovies(stat);
        }
    }

    public List<MovieDTO> getAllMovies() throws Exception {
        String sql = "select * from movie";
        try(Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement(sql)) {
            return getMovies(stat);
        }
    }

    private List<MovieDTO> getMovies(PreparedStatement stat) throws Exception {
        try(ResultSet result = stat.executeQuery()) {
            List<MovieDTO> tempList = new ArrayList<>();

            while(result.next()) {
                tempList.add(new MovieDTO(result.getInt("id"),
                        result.getString("title"),
                        result.getString("genre")));
            }

            return tempList;
        }
    }
}
