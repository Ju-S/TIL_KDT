public class Quiz04 {
    public static void main(String[] args) {
        String[] names = new String[] {"Susan", "Mike", "Tom", "Jane", "Luke", "Mia"};
        String[] shortNames = getShortNames(names, 3);

        for(int i = 0; i < shortNames.length; i++) {
            System.out.println(shortNames[i]);
        }
    }

    public static String[] getShortNames(String[] names, int maxLen) {
        int resultCnt = 0;

        for(int i = 0; i < names.length; i++) {
            if(names[i].length() <= maxLen) {
                resultCnt++;
            }
        }

        String[] resultNames = new String[resultCnt];
        resultCnt = 0;
        for(int i = 0; i < names.length; i++) {
            if(names[i].length() <= maxLen) {
                resultNames[resultCnt++] = names[i];
            }
        }
        return resultNames;
    }
}
