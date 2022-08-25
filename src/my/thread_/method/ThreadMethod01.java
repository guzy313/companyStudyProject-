package my.thread_.method;

public class ThreadMethod01 {
    public static void main(String[] args) throws Exception{
        Tx tx = new Tx();
        tx.setName("zn");
        tx.setPriority(Thread.MIN_PRIORITY);
        tx.start();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("main"+i);
        }
        tx.interrupt();
    }
}

class Tx extends Thread{
    @Override
    public void run() {

        while (true){
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+"\trun"+i);
            }
            try {
                System.out.println("休眠中...");
                Thread.sleep(20000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}