package my.thread_.method;

public class ThreadMethod02 {
    public static void main(String[] args) throws Exception{
        T02 t02 = new T02();
        t02.setName("T1");
        t02.start();

        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + "\t"+i);
            Thread.sleep(2000);
            if(i == 5){
                System.out.println("线程插队开始");
                //t02.join();
                Thread.yield();
            }
        }


    }
}
class T02 extends Thread{

    @Override
    public void run() {
        int count = 0;
        while (true){
            try {
                count ++;
                System.out.println(Thread.currentThread().getName()+"\trun"+count);
                sleep(2000);
                if(count == 20){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("子线程结束");
    }
}
