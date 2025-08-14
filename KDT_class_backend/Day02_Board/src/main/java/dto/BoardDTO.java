package dto;

import java.sql.Timestamp;

public class BoardDTO {
    private int seq;
    private String writer;
    private String title;
    private String contents;
    private int viewCount;
    private Timestamp writerDate;

    public BoardDTO() {
    }

    public BoardDTO(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public BoardDTO(int seq, String writer, String title, String contents) {
        this.seq = seq;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public BoardDTO(int seq, String title, String contents) {
        this.seq = seq;
        this.title = title;
        this.contents = contents;
    }

    public BoardDTO(int seq, String writer, String title, String contents, Timestamp writerDate, int viewCount) {
        this.seq = seq;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writerDate = writerDate;
        this.viewCount = viewCount;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Timestamp getWriterDate() {
        return writerDate;
    }

    public void setWriterDate(Timestamp writerDate) {
        this.writerDate = writerDate;
    }
}
