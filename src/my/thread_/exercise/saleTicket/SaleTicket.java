package my.thread_.exercise.saleTicket;

public class SaleTicket {
    public static void main(String[] args){
        SaleStation saleStation = new SaleStation();
        Thread saleWindow1 = new Thread(saleStation);
        Thread saleWindow2 = new Thread(saleStation);
        Thread saleWindow3 = new Thread(saleStation);
        saleWindow1.start();
        saleWindow2.start();
        saleWindow3.start();

//        SaleStation1 saleWindow1 = new SaleStation1();
//        saleWindow1.start();
//        SaleStation1 saleWindow2 = new SaleStation1();
//        saleWindow2.start();
//        SaleStation1 saleWindow3 = new SaleStation1();
//        saleWindow3.start();


    }
}

class SaleStation implements Runnable{
    private  int ticketNum = 100;


    @Override
    public synchronized void run() {
        while (saleTicket()){
           try {
               System.out.println(Thread.currentThread().getName()+"售票一张,剩余"+ticketNum+"张");
               Thread.sleep(50);
           }catch (Exception e){

           }
        }
    }

    private  boolean saleTicket(){
        if(ticketNum < 1){
            return false;
        }else{
            ticketNum --;
            return true;
        }
    }

}

class SaleStation1 extends Thread{
    private static int ticketNum = 100;


    @Override
    public void run() {
        while (saleTicket()){
            try {
                System.out.println(Thread.currentThread().getName()+"售票一张,剩余"+ticketNum+"张");
                Thread.sleep(50);
            }catch (Exception e){

            }
        }
    }

    private  boolean saleTicket(){
        if(ticketNum <= 0){
            return false;
        }else{
            ticketNum --;
            return true;
        }
    }

}