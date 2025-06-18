public class Quiz03 {
    public static void main(String[] args) {
        int[] arr = new int[]{21, 15, 3, 9, 53, 2, 54, 1, 9, 5, 23, 53, 34, 54};

        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
