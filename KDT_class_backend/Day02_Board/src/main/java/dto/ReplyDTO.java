package dto;

import java.sql.Timestamp;

public class ReplyDTO {
    private int seq;
    private String writer;
    private String contents;
    private Timestamp writeDate;
    private int parentSeq;

    public ReplyDTO() {
    }

    public ReplyDTO(int seq, String writer, String contents, Timestamp writeDate, int parentSeq) {
        this.seq = seq;
        this.writer = writer;
        this.contents = contents;
        this.writeDate = writeDate;
        this.parentSeq = parentSeq;
    }

    public ReplyDTO(String writer, String contents, int parentSeq) {
        this.writer = writer;
        this.contents = contents;
        this.parentSeq = parentSeq;
    }

    public ReplyDTO(int seq, String contents) {
        this.seq = seq;
        this.contents = contents;
    }

    public int getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(int parentSeq) {
        this.parentSeq = parentSeq;
    }

    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
