import java.io.FileInputStream;
import javazoom.jl.player.Player; // JLayer 라이브러리의 Player 클래스 임포트

public class Exam03 {
    public static void main(String[] args) {
        try {
            // 재생할 MP3 파일 경로 설정 (예: 프로젝트 폴더 안에 "your_music.mp3" 파일이 있다고 가정)
            FileInputStream fis = new FileInputStream("C:\\20250609_KDT\\workspace01_java_intellij\\KDT_class\\Day08_OOP\\밤.mp3");

            // JLayer의 Player 객체 생성
            Player playMp3 = new Player(fis);

            // MP3 재생 시작
            System.out.println("MP3 재생 중...");
            playMp3.play();

            System.out.println("MP3 재생 완료.");

        } catch (Exception e) {
            System.err.println("MP3 재생 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}