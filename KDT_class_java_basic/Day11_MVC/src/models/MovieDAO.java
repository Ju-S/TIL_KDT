package models;

import classes.MovieDTO;

import java.util.ArrayList;

// DAO: (Data Access Object)
// 데이터의 CRUD를 담당
public class MovieDAO {
    private ArrayList<MovieDTO> movieDTOS = new ArrayList<>();
    // 현재 등록된 영화의 수

    public void addMovie(MovieDTO movieDTO) {
        movieDTOS.add(movieDTO);
    }

    public ArrayList<MovieDTO> getMovies() {
        return movieDTOS;
    }

    // id 중복 확인(중복이라면 true, 아니라면 false)
    public boolean checkIdDuplicated(int id) {
        for (int i = 0; i < movieDTOS.size(); i++) {
            // 등록된 영화의 ID중 매개변수 id와 중복되는 값이 있다면 true
            if (movieDTOS.get(i).getId() == id) {
                return true;
            }
        }
        // 모든 영화의 ID와 비교했을때 중복된 값이 없다면 false
        return false;
    }
}
