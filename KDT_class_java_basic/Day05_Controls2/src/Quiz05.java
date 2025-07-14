import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bestScore = 99;  // 최단기록 저장용, 초기값 99

        while (true) {
            System.out.println("== Up & Down Game ==\n");
            System.out.println("1. Game Start");
            System.out.println("2. Game Score");
            System.out.println("3. End Game\n");
            System.out.print("> ");
            int selectedMenu = Integer.parseInt(sc.nextLine());  // 사용자가 선택한 메뉴

            switch (selectedMenu) {
                case 1:  // 업다운 게임 내용
                    int answer = (int) (Math.random() * 99 + 1);  // 정답용 난수 1-99사이
                    int predictionCnt = 0;  // bestScore용 기록 저장 변수

                    System.out.println("<< Game Start >>");

                    while (true) {
                        int predict;

                        System.out.print("Input Number : ");
                        predict = Integer.parseInt(sc.nextLine());  // 플레이어 예측답 입력

                        if (predict < answer) {  // 플레이어가 입력한 값이 정답보다 낮은 경우
                            System.out.println("<<     UP     >>");
                        } else if (predict > answer) {  // 플레이어가 입력한 값이 정답보다 높은 경우
                            System.out.println("<<    DOWN    >>");
                        } else {  // 플레이어가 정답을 맞춘 경우
                            System.out.println("<<    정답!    >>");
                            if(predictionCnt < bestScore) {  // 플레이어의 기록이 기존의 최단기록보다 적은 경우
                                System.out.println("축하합니다! 최단기록을 갱신하였습니다!");
                                bestScore = predictionCnt;  // 플레이어의 기록을 최단기록에 대입
                            }
                            break;
                        }
                        predictionCnt++;  // bestScore용 기록 증가
                    }
                    break;
                case 2:  // bestScore를 출력
                    if(bestScore != 99) {
                        System.out.println("현재까지 최단 기록은 " + bestScore + "회 입니다.");
                    } else {  // bestScore가 초기값인 경우, 기록없음으로 출력
                        System.out.println("아직 기록된 최단 기록이 없습니다.");
                    }
                    break;
                case 3:  // 게임종료
                    System.exit(0);
                    break;
            }
        }
    }
}
