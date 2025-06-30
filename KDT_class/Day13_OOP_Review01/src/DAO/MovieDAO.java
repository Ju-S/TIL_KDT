package DAO;

import DTO.MovieDTO;

import java.util.ArrayList;

public class MovieDAO {
    ArrayList<MovieDTO> movieList = new ArrayList<MovieDTO>();
    int movieId = 1001;
    // movieDTO의 id 초기값

    public MovieDAO() {}

    // 영화 등록
    public void regMovie(MovieDTO newMovieInfo) {
        newMovieInfo.setId(movieId++);
        movieList.add(newMovieInfo);
    }

    // 영화 목록 반환
    public ArrayList<MovieDTO> getMovieList() {
        return movieList;
    }

    //-----------검색-------------
    // 영화 검색(제목)(resultList.isEmpty()로 검색결과가 있는지 확인)
    public ArrayList<MovieDTO> findMoviesByTitle(String title) {
        ArrayList<MovieDTO> resultList = new ArrayList<>();

        for(MovieDTO movie : movieList) {
            if(movie.getTitle().contains(title)) {
                resultList.add(movie);
            }
        }

        return resultList;
    }

    // 영화 검색(장르)(resultList.isEmpty()로 검색결과가 있는지 확인)
    public ArrayList<MovieDTO> findMoviesByGenre(String genre) {
        ArrayList<MovieDTO> resultList = new ArrayList<>();

        for(MovieDTO movie : movieList) {
            if(movie.getGenre().contains(genre)) {
                resultList.add(movie);
            }
        }

        return resultList;
    }

    // 영화 단일 검색(id)(null 이라면 검색안됨)
    public MovieDTO findMovieById(int targetId) {
        for(MovieDTO movie : movieList) {
            if(movie.getId() == targetId) {
                return movie;
            }
        }
        return null;
    }

    // 단일 영화 Index 검색(id)(-1 이라면 검색안됨)
    public int findIndexById(int targetId) {
        MovieDTO targetMovie = findMovieById(targetId);

        if(targetMovie != null) {
            return movieList.indexOf(targetMovie);
        } else {
            return -1;
        }
    }
    //---------------------------

    // 영화 수정
    public void modifyMovieInfo(MovieDTO modifiedInfo) {
        int targetIndex = findIndexById(modifiedInfo.getId());

        if(targetIndex != -1) {
            movieList.set(targetIndex, modifiedInfo);
        }
    }

    // 영화 삭제(true 반환 시, 삭제 성공)
    public boolean removeMovie(int targetId) {
        int targetIndex = findIndexById(targetId);

        if(targetIndex != -1) {
            movieList.remove(targetIndex);
            return true;
        } else {
            return false;
        }
    }
}
