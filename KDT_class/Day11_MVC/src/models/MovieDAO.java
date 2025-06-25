package models;

import classes.MovieDTO;

// DAO: (Data Access Object)
// 데이터의 CRUD를 담당
public class MovieDAO {
    private MovieDTO[] movieDTOS = new MovieDTO[5];
    private int regMovieCnt = 0;
    // 현재 등록된 영화의 수

    public void addMovie(MovieDTO movieDTO) {
        movieDTOS[regMovieCnt++] = movieDTO;
    }

    public int getRegMovieCnt() {
        return regMovieCnt;
    }

    public MovieDTO[] getMovies() {
        return movieDTOS;
    }

    // id 중복 확인(중복이라면 true, 아니라면 false)
    public boolean checkIdDuplicated(int id) {
        for(int i = 0; i < regMovieCnt; i++) {
            // 등록된 영화의 ID중 매개변수 id와 중복되는 값이 있다면 true
            if(movieDTOS[i].getId() == id) {
                return true;
            }
        }
        // 모든 영화의 ID와 비교했을때 중복된 값이 없다면 false
        return false;
    }
}
