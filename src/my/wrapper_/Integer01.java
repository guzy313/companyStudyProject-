package my.wrapper_;

public class Integer01 {
    public static void main(String[] args){
       /*int n1 = 1;
       Integer integer = new Integer(n1);
       System.out.println(integer);
       Integer integer1 = Integer.valueOf(n1);
        System.out.println(integer1);
       int ii = integer.intValue();
       System.out.println(ii);

       int nn1 = 3;
       String str1 = Integer.toString(nn1);
       String strX = "333";
       int xxx = new Integer(strX);*/
        /*
        System.out.println(Character.isWhitespace(' '));
        System.out.println(Character.isDigit('t'));

        System.out.println(isNum("68474"));*/
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a==b);

        Integer m = Integer.valueOf(1);
        Integer n = 1;
        System.out.println(m==n);

        Integer x = -129;
        Integer y = -129;
        System.out.println(x==y);


        Integer xx = 127;
        int xy = 127;
        System.out.println(xx == xy);


    }

    public static boolean isNum(String n){
        for (int i = 0; i < n.length(); i++) {
             if(!Character.isDigit(n.charAt(i))){
                 return false;
             }
        }
        return true;
    }
}
