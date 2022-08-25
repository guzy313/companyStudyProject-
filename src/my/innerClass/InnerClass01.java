package my.innerClass;

public class InnerClass01 {
    public static void main(String[] args){

        show(new InnerT01() {
            @Override
            public void cry() {
                System.out.println("匿名内部类 接口 ");
            }
        });



    }

    public static void show(InnerT01 a){
        a.cry();
    }
}

interface InnerT01{
   void cry();
}



