package my.innerClass;

public class InnerClass02 {
    public static void main(String[] args){
        InnerClass02_Outer01 innerClass02_outer01 = new InnerClass02_Outer01();
        InnerClass02_Outer01.Inner01_01  in = innerClass02_outer01.new Inner01_01();

        InnerClass02_Outer01.Inner01_01 in1 = new InnerClass02_Outer01().new Inner01_01();

        long tm1 = System.currentTimeMillis();
        new InnerClass02_Outer01(){
            @Override
            public void xx(){
                super.xx();
                System.out.println("xx已改");
            }
        }.xx();

        InnerClass02_02 et02 =  new InnerClass02_02(){
            @Override
            public void eat11(Animal02 animal02){
                System.out.println("重写接口eat11");
                System.out.println(animal02);
            };
        };
        et02.eat11(Animal02.CAT);

        new InnerClass02_Outer01.Inner01_02().soutnm();
        long tm2 = System.currentTimeMillis();
        System.out.println(tm2 - tm1);

    }
}


class InnerClass02_Outer01{
    private  Inner01_01 in01 = new Inner01_01();//饿汉
    private String nm = "InnerClass02_Outer01nm";

    public void xx(){
        System.out.println("xx1");
    }

    //一般成员内部类
    class Inner01_01{

    }

    static class Inner01_02{
        private String nm = "Inner01_02nm";

        public void soutnm(){
            System.out.println("this"+nm);
            System.out.println("outer1"+new InnerClass02_Outer01().nm);
        }

    }

    public Inner01_01 newInner01_01(){
        return in01;
    }
}

interface InnerClass02_02{

    void eat11(Animal02 animal02);

}

enum Animal02{
    HUMAN("人"),DOG("狗"),CAT("猫");
    private String name;

    Animal02(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
