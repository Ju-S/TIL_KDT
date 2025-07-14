package quiz;

import classes.Contact;
import classes.Movie;
import classes.Music;

public class Quiz01 {
    public static void main(String[] args) {
        // 제목, 장르 두개의 필드를 가지는 movie클래스를 만들고,
        // 정보 은닉에 따라 setter getter 작성.

        Movie movie = new Movie("Straw", "Thriller");
        System.out.println(movie.getTitle() + " : " + movie.getGenre());

        Music music = new Music("밤", "손승진");
        System.out.println(music.getTitle() + " : " + music.getSinger());

        Contact contact = new Contact("김주성", "010-4593-0643");
        System.out.println(contact.getName() + " : " + contact.getPhNo());
    }
}
