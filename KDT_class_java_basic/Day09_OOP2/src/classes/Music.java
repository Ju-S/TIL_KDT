package classes;

public class Music {
    private String title;
    private String singer;

    public Music(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
