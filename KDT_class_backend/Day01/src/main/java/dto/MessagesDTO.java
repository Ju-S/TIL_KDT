package dto;

public class MessagesDTO {
    private int seq;
    private String writer;
    private String message;

    public MessagesDTO() {}
    public MessagesDTO(int seq, String writer, String message) {
        this.seq = seq;
        this.writer = writer;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
