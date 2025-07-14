package DTO;

import java.util.Date;

public class MovieDTO {
    int id;
    String title;
    String genre;
    Date publishDate;

    public MovieDTO() {}
    public MovieDTO(int id, String title, String genre, Date publishDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publishDate = publishDate;
    }

    public MovieDTO(String title, String genre, Date publishDate) {
        this.title = title;
        this.genre = genre;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
