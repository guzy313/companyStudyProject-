package my.thread_;

public class Thread01 {
    public static void main(String[] args){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   int count = 0;
                   for (;;) {
                       count ++;
                       System.out.println("hlwd"+count+"\t"+Thread.currentThread().getName());
                       if(count == 10){
                           break;
                       }
                       Thread.sleep(1000);
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                try {
                   while (true){
                       count++;
                       System.out.println("hi"+count+"\t"+Thread.currentThread().getName());
                      if(count == 5){
                          break;
                      }
                       Thread.sleep(1000);
                   }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("main continue..");


    }
}


//线程代理 模拟
class ThreadProxy implements Runnable{
    private Runnable target = null;


    @Override
    public void run() {
        if(target != null){
            target.run();
        }
    }

    public ThreadProxy() {
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start(){
        start0();
    }

    public void start0(){
        run();
    }

}


class Thr01 extends Thread{
    @Override
    public void run() {
        try {

            System.out.println("running..");
            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


class Doggg implements Runnable{
    private int count;
    @Override
    public void run() {
        while (true){
           try {
               count ++ ;
               System.out.println("www\t"+count+Thread.currentThread().getName());
               Thread.sleep(2000);
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }
}
