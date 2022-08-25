package my.thread_.exercise;

public class Exercise01 {
    public static void main(String[] args) throws Exception{
        Tjoin tjoin = new Tjoin();
        Thread thread = new Thread(tjoin);


        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()+"\thi\t"+i);
            Thread.sleep(1000);
            if(i == 5){
                thread.start();
                thread.join();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            for (int j = 1; j <= 10; j++) {
//                                System.out.println(Thread.currentThread().getName()+"\thellow\t"+j);
//                                Thread.sleep(1000);
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
            }
        }
        
    }
}

class Tjoin implements Runnable{
    @Override
    public void run() {
        try {
            for (int j = 1; j <= 10; j++) {
                System.out.println(Thread.currentThread().getName()+"\thellow\t"+j);
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
