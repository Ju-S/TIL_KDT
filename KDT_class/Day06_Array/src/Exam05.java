public class Exam05 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 6, 2};
        int[] arr2 = new int[]{4, 1, 9};

        int totalMatchedNum = 0;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    totalMatchedNum++;
                }
            }
        }

        System.out.println("두 배열간 일치하는 숫자는 총 " + totalMatchedNum + "개 입니다.");
    }
}
