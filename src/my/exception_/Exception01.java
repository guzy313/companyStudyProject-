package my.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exception01 {
    public static void main(String[] args){
       /* int n1 = 10;
        int n2 = 0;

        try {
            int res = n1 / n2 ;
            System.out.println(res);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("程序继续运行..");


        A12 x = new B12();
        B12 xx = (B12)x;
        C12 xxx = (C12)x;*/
        //System.out.println(mt());
        //new Exception01().NumberFormatException_();
        while (true){
           try {
               Scanner scanner = new Scanner(System.in);
               System.out.print("请输入一个整数:");
               int aa = Integer.parseInt(scanner.next());
              break;
           }catch (NumberFormatException e){
               System.out.println("输入错误");
           }
        }




    }
    public void NumberFormatException_(){
        try{
            System.out.println("请输入一个整数:");
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.next();
            int x = Integer.parseInt(scan);


        }catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("输入错误");
            NumberFormatException_();
        }catch (Exception e){
            System.out.println(88);
        }
    }

    public static int mt(){
        try{
            int[] x = new int[3];
            System.out.println(x[5]);
            return 1;
        }catch (ArrayIndexOutOfBoundsException e){
            return 2;
        }catch (Exception e){
            return 3;
        }finally{
            return -1;
        }
    }
}

class A12{

    public void n1()throws FileNotFoundException{
        FileInputStream fis = new FileInputStream("");
    }
}
class B12 extends A12{

}
class C12 extends A12{

}
class X11{

    public void x11() throws RuntimeException{

    }
}

class Y11 extends X11{
    @Override
    public void x11() throws ArithmeticException {
        super.x11();
    }
}
