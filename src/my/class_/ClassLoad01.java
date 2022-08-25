package my.class_;

import org.junit.jupiter.api.Test;

public class ClassLoad01 {


    @Test
    public void classLoad(){
        System.out.println(BBB.num);
    }

}


class BBB{

    static {
        System.out.println("静态代码块加载");
        num = 300;
    }
    static int num = 100;


    public BBB() {
        System.out.println("BBB构造器调用");
    }
}
