public class Quiz01 {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = i;
//        }
//
//        for(int i = 0; i < arr.length; i ++) {
//            System.out.println(arr[i]);
//        }
//
//        System.out.println(arr[0]);
//        System.out.println(arr[99]);

        int[] arr = new int[100];

        for(int i = arr.length; i > 0; i--){
            arr[arr.length - i] = i;
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = arr.length - i;
        }

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        System.out.println(arr[0]);
        System.out.println(arr[99]);
    }
}
