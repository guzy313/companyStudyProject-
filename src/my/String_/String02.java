package my.String_;

public class String02 {
    public static void main(String[] args){
        String str = "abcde";
        str = reserve(str,3,4);
        System.out.println(str);
    }


    public static String reserve(String str,int m,int n){
        char[] array = str.toCharArray();
        for (int i = m - 1; i < n - 1; i++) {
            char here = array[i];
            array[i] = array[i + 1];
            array[i + 1] = here;
        }
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sbd.append(array[i]);
        }
        return sbd.toString();
    }
}
