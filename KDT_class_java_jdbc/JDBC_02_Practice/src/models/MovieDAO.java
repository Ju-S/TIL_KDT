package models;

import classes.MovieDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// DAO: (Data Access Object)
// 데이터의 CRUD를 담당
public class MovieDAO {

    private Connection getConnection() throws Exception {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbID = "kedu";
        String dbPW = "kedu";
        return DriverManager.getConnection(dbURL, dbID, dbPW);
    }

    public void addMovie(MovieDTO movieDTO) throws Exception {
        try(Connection con = this.getConnection();
            Statement stat = con.createStatement()) {
            String sql = "insert into movie values(movie_seq.nextval, '" + movieDTO.getTitle() + "', '" + movieDTO.getGenre() + "')";
            stat.executeUpdate(sql);
        }
    }

    public void updateMovie(MovieDTO movieDTO) throws Exception {
        try(Connection con = this.getConnection();
            Statement stat = con.createStatement()) {
            String sql = "update movie set title = '" + movieDTO.getTitle() +
                                        "', genre = '" + movieDTO.getGenre() +
                                    "' where id = " + movieDTO.getId();
            stat.executeUpdate(sql);
        }
    }

    public void deleteMovieById(int targetId) throws Exception {
        try(Connection con = this.getConnection();
            Statement stat = con.createStatement()) {
            String sql = "delete from movie where id = " + targetId;
            stat.executeUpdate(sql);
        }
    }

    public MovieDTO findMovieByID(int target) throws Exception {
        String sql = "select * from movie where id = '" + target + "'";
        return getMovies(sql).getFirst();
    }

    public List<MovieDTO> findMoviesByTitle(String target) throws Exception {
        String sql = "select * from movie where title like '%" + target + "%'";
        return getMovies(sql);
    }

    public List<MovieDTO> findMoviesByGenre(String target) throws Exception {
        String sql = "select * from movie where genre like '%" + target + "%'";
        return getMovies(sql);
    }

    public List<MovieDTO> getAllMovies() throws Exception {
        String sql = "select * from movie";
        return getMovies(sql);
    }

    private List<MovieDTO> getMovies(String sql) throws Exception {
        try(Connection con = this.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery(sql)) {
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
