package classes;


public class Tv {

    // 클래스 설계 규칙 권고안
    // 1. 정보은닉 규칙에 따라야 한다.
    // 접근제한자↓ 문법과 관련있음.
    // public / protected / package(default) / private
    // public : 클래스 내의 요소에 프로젝트 전체에서 접근을 허용함
    // private : 프로젝트 전체에서 접근을 차단하고 클래스 {} 내부에서만 접근을 허용함
    // package : 클래스가 속한 패키지와 같은 패키지 까지만 접근 허용
    // 정보은닉에 따른 클래스 설계는 캡슐화(외부에 노출될필요가 있는것만 노출한다는 원칙)에도 영향을 미침

    private String brand;
    private int channel;
    private int volume;

    // this : 자기참조변수. 인스턴스 생성 시 자동으로 생성됨

    public Tv getThis() {
        return this;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }


    // 클래스 구성 요소
    // 1. Member Field(variable) : class를 구성하는 멤버인 변수
    // > 지역변수 : 메서드 또는 메서드 내부의 {} 내에 생성되는 변수
    // 지역변수는 메모리 stack부에 생성되는데, 각 함수마다의 스택프레임이 생겨나 코드 흐름에 따라 가르키고 있는 스택프레임이 다르다.
    // 따라서, 같은 이름의 변수명도 다른 스택프레임에 생성되기 때문에 구분이 가능하다.

    // 2. Member Method : class를 구성하는 멤버인 함수
    // e.g,
//    public void powerOn() {}
//    void powerOff() {}

    // 3. Constructor(생성자)
    // 4. Nested Class(중첩 클래스)

}
