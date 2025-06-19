import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quiz02 {
    public static int countChar(String str, char regex) {
        int cnt = 0;
        char[] arr = str.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == regex){
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String str = "AlkejflsdjfaweoifjdaslfiohwefqieffaewifjdalfaEILFJDLJlwfjLDJfqlfijSLDIfj" +
                "leIFJDSLJflEIJowfjiSDLfkJlwfjidlSIEjfdiSLIDjflEIfjldskjlEJFLQlfjdLASDFJEILAlk" +
                "ejflsdjfaweoifjdaslfiohwefqieffaewifjdalfaEILFJDLJlwfjLDJfqlfijSLDIfjleIFJDSL" +
                "JflEIJowfjiSDLfkJlwfjidlSIEjfdiSLIDjflEIfjldskjlEJFLQlfjdLASDFJEILAlkejflsdjf" +
                "aweoifjdaslfiohwefqieffaewifjdalfaEILFJDLJlwfjLDJfqlfijSLDIfjleIFJDSLJflEIJow" +
                "fjiSDLfkJlwfjidlSIEjfdiSLIDjflEIfjldskjlEJFLQlfjdLASDFJEILAlkejflsdjfaweoifjd" +
                "aslfiohwefqieffaewifjdalfaEILFJDLJlwfjLDJfqlfijSLDIfjleIFJDSLJflEIJowfjiSDLfk" +
                "JlwfjidlSIEjfdiSLIDjflEIfjldskjlEJFLQlfjdLASDFJEILAlkejflsdjfaweoifjdaslfiohw" +
                "efqieffaewifjdalfaEILFJDLJlwfjLDJfqlfijSLDIfjleIFJDSLJflEIJowfjiSDLfkJlwfjidl" +
                "SIEjfdiSLIDjflEIfjldskjlEJFLQlfjdLASDFJEILA";

        int result = countChar(str, 'A');
        System.out.println(result);
    }
}
