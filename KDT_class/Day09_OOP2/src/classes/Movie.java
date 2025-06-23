package classes;

public class Movie {
    private String genre;
    private String title;

    // Constructor : 생성자 메서드
    // 1. 메서드 이름은 반드시 클래스 이름과 동일해야 한다.
    // 2. 생성자 메서드는 반환값을 가지지 않는다.
    // 3. 생성자 메서드는 호출시점이 정해져 있다.
    // 4. 그외 모든 문법은 일반 메서드와 동일한 규칙을 가진다.(매개변수, 오버로딩 등..)
    // 정의. 인스턴스 생성 시점에 인스턴스에 대한 초기화 작업을 수행하는 메서드

    public Movie() { super(); }  // default constructor(기본 생성자)
    // 만약 위처럼 직접 명시했다면, '명시적 생성자'라고 한다.
    // 명시적 생성자가 하나라도 존재한다면, 기본 생성자는 생성되지 않는다.

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
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
}
