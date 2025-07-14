package exams;

import classes.Movie;

public class Exam02 {
    public static void main(String[] args) {
        Movie[] movies = new Movie[3];
        movies[0] = new Movie("어벤져스", "액션");
        movies[1] = new Movie("아이언맨", "느와르");
        movies[2] = new Movie("헐크", "로맨스");

        System.out.println(movies[1].getGenre());

        for(Movie movie : movies) {
            System.out.println(movie.getTitle() + " : " + movie.getGenre());
        }
    }
}
