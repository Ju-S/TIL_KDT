public class Exam01 {
    public static void main(String[] args) {
        // Server - 네트워크 상에서 특정 서비스를 제공
        // Client - 서버에 접근해서 특정 서비스를 이용

        // IP - internet protocol. 네트워크 상에서 장치를 식별하는 고유 주소 값
        // PORT - 한 대의 장치에서 동작하는 수많은 네트워크 프로그램을 구분짓는 식별자
        // 0-1024: well-known port

        // Socket - 네트워크 프로그램이 데이터를 주고받기 위해 사용하는 논리적 단말기

        // ServerSocket - 보통 1:N 통신을 수행하는 서버측 로직 상, 단순 소켓이 아닌 접속 요청별로 Socket 을 찍어내는 Socket 공장이 필요함.
        // ServerSocket 이 소켓을 생성하는 공장 역할을 담당

        // Stream - Socket 으로 연결이 확보되면, 데이터 통싱을 위한 통로로 Stream을 사용한다.
    }
}
