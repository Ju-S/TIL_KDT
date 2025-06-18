public class Quiz04 {
    public static void main(String[] args) {
        //?회차의 로또 번호는: (?) (?) (?) (?) (?) (?) || 보너스 번호는 ? || 내가 고른 숫자 6개: ? ? ? ? ? ? || 맞은 갯수: ? || 등수: ?등
        //1등 총 상금: 2,000,000,000원     총 확률: ?%
        //2등 총 상금: 55,000,000원      총 확률: ?%
        //3등 총 상금: 1,400,000원       총 확률: ?%
        //4등 총 상금: 50,000원          총 확률: ?%
        //5등 총 상금: 5,000원           총 확률: ?%
        //꽝                            총 확률: ?%
        //사용한 금액: ?원
        //상금: ?원
        //상금 - 사용금액: ?원

        long totalCnt = 0;
        int[] prize = new int[6];

        while (true) {
            totalCnt++;
            int[] rand = new int[7];
            int bonus;

            //정답 번호 뽑기 + 보너스 번호
            for (int i = 0; i < rand.length; i++) {
                rand[i] = (int) (Math.random() * 45 + 1);
                for (int j = 0; j < i; j++) {
                    if (rand[i] == rand[j]) {
                        i--;
                        break;
                    }
                }
            }
            bonus = rand[6];

            int[] choice = new int[6];
            //내 번호 뽑기
            for (int i = 0; i < choice.length; i++) {
                choice[i] = (int) (Math.random() * 45 + 1);
                for (int j = 0; j < i; j++) {
                    if (choice[i] == choice[j]) {
                        i--;
                        break;
                    }
                }
            }

            System.out.print(totalCnt + "회차의 로또 번호는: ");
            for (int i = 0; i < rand.length - 1; i++) {
                System.out.print("(" + rand[i] + ")");
            }
            System.out.print(" || 보너스 번호는 " + bonus + " || 내가 고른 숫자 6개: ");
            for (int i = 0; i < choice.length; i++){
                System.out.print(choice[i] + " ");
            }

            System.out.print("|| 맞은 갯수: ");
            int totalMatchedNum = 0;
            for (int i = 0; i < rand.length - 1; i++) {
                for (int j = 0; j < choice.length; j++) {
                    if (rand[i] == choice[j]) {
                        totalMatchedNum++;
                    }
                }
            }
            for(int i = 0; i < choice.length; i++){
                if(choice[i] == bonus) {
                    bonus = -1;
                }
            }

            System.out.print(totalMatchedNum + "개 || 등수: ");
            switch(totalMatchedNum){
                case 0, 1, 2:
                    System.out.print("꽝");
                    prize[5]++;
                    break;
                case 3:
                    System.out.print("5등");
                    prize[4]++;
                    break;
                case 4:
                    System.out.print("4등");
                    prize[3]++;
                    break;
                case 5:
                    if(bonus != -1) {
                        System.out.print("3등");
                        prize[2]++;
                    } else {
                        System.out.print("2등");
                        prize[1]++;
                    }
                    break;
                case 6:
                    System.out.print("1등");
                    prize[0]++;
                    break;
            }
            System.out.println();

            if(prize[0] > 0){  // 1등에 당첨된 경우
                break;
            }
        }
        System.out.println("1등 총 상금: 2,000,000,000원 총 확률: " + ((double)prize[0] / totalCnt * 100) + "% " + prize[0] + "회 당첨");
        System.out.println("2등 총 상금: 55,000,000원 총 확률: " + ((double)prize[1] / totalCnt * 100) + "% " + prize[1] + "회 당첨");
        System.out.println("3등 총 상금: 1,400,000원 총 확률: " + ((double)prize[2] / totalCnt * 100) + "% " + prize[2] + "회 당첨");
        System.out.println("4등 총 상금: 50,000원 총 확률: " + ((double)prize[3] / totalCnt * 100) + "% " + prize[3] + "회 당첨");
        System.out.println("5등 총 상금: 5,000원 총 확률: " + ((double)prize[4] / totalCnt * 100) + "% " + prize[4] + "회 당첨");
        System.out.println("꽝 총 확률: " + ((double)prize[5] / totalCnt * 100) + "% " + prize[5] + "회 당첨");
        System.out.println("사용한 금액: " + (totalCnt * 1000) + "원");

        long totalPrize = 0;
        long[] approxPrize = new long[]{2000000000, 55000000, 1400000, 50000, 5000, 0};
        for(int i = 0; i < prize.length; i++){
            totalPrize += prize[i] * approxPrize[i];
        }

        System.out.println("상금: " + totalPrize + "원");
        System.out.println("상금 - 사용금액: " + (totalPrize - (totalCnt * 5000)) + "원");
    }
}
