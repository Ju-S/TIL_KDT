package dto;

public class FilesDTO {
    private int seq;
    private String writer;
    private String oriName;
    private String sysName;
    private int parentSeq;

    public FilesDTO() {

    }

    public FilesDTO(int seq, String writer, String oriName, String sysName, int parentSeq) {
        this.seq = seq;
        this.writer = writer;
        this.oriName = oriName;
        this.sysName = sysName;
        this.parentSeq = parentSeq;
    }

    public FilesDTO(String writer, String oriName, String sysName, int parentSeq) {
        this.writer = writer;
        this.oriName = oriName;
        this.sysName = sysName;
        this.parentSeq = parentSeq;
    }

    public int getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(int parentSeq) {
        this.parentSeq = parentSeq;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getOriName() {
        return oriName;
    }

    public void setOriName(String oriName) {
        this.oriName = oriName;
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
