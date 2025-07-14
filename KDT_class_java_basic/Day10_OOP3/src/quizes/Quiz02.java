package quizes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) throws Exception {
        // 사용자로부터 연월일을 입력받고 (yyyy년MM월dd일)
        // 입력받은 날로부터 1주일 전 날짜를
        // yyyy/MM/dd 형식으로 출력.

        Scanner sc = new Scanner(System.in);

        System.out.print("날짜를 입력하세요.(yyyy년MM월dd일) : ");
        String date = sc.nextLine();

        SimpleDateFormat dateToTime = new SimpleDateFormat("yyyy년MM월dd일");
        Date formattingDate = dateToTime.parse(date);
        Date oneWeek = new Date(86400000 * 7);

        SimpleDateFormat timeToDate = new SimpleDateFormat("yyyy/MM/dd");
        String result = timeToDate.format(formattingDate.getTime() - oneWeek.getTime());
        System.out.println(result);
    }
}
