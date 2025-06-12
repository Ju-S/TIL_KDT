import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz2775 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        List<Integer> peopleList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();

        for(int i = 0; i < T; i++) {
            int k = sc.nextInt();
            int n = sc.nextInt();

            //거주민 수 초기화(입력된 n 호수까지.)
            peopleList.clear();
            for(int j = 0; j < n; j++) { peopleList.add(j, j + 1); }

            //0~k층 까지 각 n호까지 살고 있는 인원 수 계산.
            for(int j = 0; j < k; j++) {
                for(int h = 1; h < n; h++) {
                    peopleList.set(h, peopleList.get(h - 1) + peopleList.get(h));
                }
            }
            resultList.add(peopleList.get(n - 1));
        }

        for(int i = 0; i < T; i++){
            System.out.println(resultList.get(i));
        }
    }
}

// 이 아파트에 거주를 하려면 조건이 있는데,
// “a층의 b호에 살려면 자신의 아래(a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람들을 데려와 살아야 한다” 는 계약 조항을 꼭 지키고 들어와야 한다.

// 아파트에 비어있는 집은 없고 모든 거주민들이 이 계약 조건을 지키고 왔다고 가정했을 때,
// 주어지는 양의 정수 k와 n에 대해 k층에 n호에는 몇 명이 살고 있는지 출력하라.

// 단, 아파트에는 0층부터 있고 각층에는 1호부터 있으며, 0층의 i호에는 i명이 산다.

//0
//1 2 3 4 5 6 7 8 9 10 11 12 13 14

//1
//1 3 6 10 15 21

//2
//1 4 10 20 35 56
