package my.thread_.exercise;

public class Exercise02 {
    public static void main(String[] args) throws Exception{
        MyDaemon01 daemon = new MyDaemon01();
        daemon.setDaemon(true);
        daemon.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println("working.."+i);
            Thread.sleep(1000);
        }
    }
}

class MyDaemon01 extends Thread{

    @Override
    public void run() {
        for (;;) {
            try {
                System.out.println("running...");
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
    }
}