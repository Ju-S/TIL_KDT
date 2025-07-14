public class Quiz01 {
    public static int getRandomDice() {
        return (int) (Math.random() * 6 + 1);
    }

    public static int getRandomFrom(int from, int to) {
        return (int) (Math.random() * (to - from + 1) + from);
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        //1-6 사이의 랜덤 값을 반환하는 getRandomDice()함수를 제작하고 사용
        //최소값과 최대값 두 정수를 전달하면 두수 사이의 랜덤값을 반환하는 getRandomFrom() 함수 제작 사용
        //int형 배열을 인자값으로 전달하면, 배열 내 모든 숫자를 더한 결과를 반환하는 getSum() 제작
        System.out.println(getRandomDice());
        System.out.println(getRandomFrom(5, 10));
        int[] test = new int[]{5, 19, 23, 17};
        System.out.println("배열 값들의 합은 : " + getSum(test));
    }
}
