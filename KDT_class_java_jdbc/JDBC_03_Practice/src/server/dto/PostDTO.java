package server.dto;

import java.util.Date;

public class PostDTO {
    private String contents;
    private String author;
    private String authorId;
    private Date createdAt;

    public PostDTO() {}
    public PostDTO(String contents, String author, String authorId, Date createdAt) {
        this.contents = contents;
        this.author = author;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }
    public PostDTO(String contents, String author, String authorId) {
        this.contents = contents;
        this.author = author;
        this.authorId = authorId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
