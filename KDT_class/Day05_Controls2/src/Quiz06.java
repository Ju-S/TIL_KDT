import java.util.Scanner;

public class Quiz06 {
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
                    int predictionCnt = 1;  // bestScore 및 차례(플레이어, 컴퓨터) 계산용 기록 저장 변수
                    int predict;  // 예측 값 저장 변수

                    int predictMin = 1, predictMax = 99;
                    // 컴퓨터의 예측범위를 지정할 수 있도록 하는 현재기준 예측범위

                    System.out.println("<< Game Start >>");

                    while (true) {
                        if(predictionCnt % 2 == 1) {  // 입력 횟수가 홀수인 경우, 플레이어의 차례
                            System.out.print("Input Number : ");
                            predict = Integer.parseInt(sc.nextLine());  // 플레이어 예측답 입력
                        } else {  // 입력 횟수가 짝수인 경우, 컴퓨터의 차례
                            System.out.print("com's prediction : ");
                            predict = (int)(Math.random() * (predictMax - predictMin - 1) + predictMin + 1);
                            // 저장된 예측값의 최소값과 최대값을 토대로 컴퓨터의 예측범위 조정
                            // predictMin = 10, predictMax = 20 인 경우, 나올 수 있는 컴퓨터의 predict는 11-19
                            System.out.println(predict);  // 범위안에 선정된 난수값 출력
                        }

                        if (predict < answer) {  // 플레이어, 컴퓨터가 입력한 값이 정답보다 낮은 경우
                            System.out.println("<<     UP     >>");
                            predictMin = predict;
                        } else if (predict > answer) {  // 플레이어, 컴퓨터가 입력한 값이 정답보다 높은 경우
                            System.out.println("<<    DOWN    >>");
                            predictMax = predict;
                        } else {  // 플레이어, 컴퓨터가 정답을 맞춘 경우
                            System.out.println("<<    정답!    >>");
                            if(predictionCnt % 2 == 1) {  // 차례가 홀수인 경우(플레이어 차례)
                                if (predictionCnt < bestScore) {  // 플레이어의 기록이 기존의 최단기록보다 적은 경우
                                    System.out.println("축하합니다! 최단기록을 갱신하였습니다!");
                                    bestScore = predictionCnt / 2 + 1;  // 플레이어의 기록을 최단기록에 대입
                                    // 컴퓨터와의 차례를 설정하기 위해 사용되었으므로 2를 나누고 홀수차례 시작이기 때문에 1을 더한다.
                                }
                            } else {
                                System.out.println("컴퓨터가 이겼습니다..");
                            }
                            break;
                        }
                        predictionCnt++;  // 차례(플레이어, 컴퓨터) 및 bestScore용 기록 증가
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
