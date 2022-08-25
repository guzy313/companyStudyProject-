package my.thread_.status;

public class ThreadState {
    public static void main(String[] args) throws Exception{
        T t = new T();
        System.out.println(t.getName() + "状态:"+t.getState());
        t.start();

        while (Thread.State.TERMINATED != t.getState()){
            System.out.println(t.getName() + "状态:"+t.getState());
            Thread.sleep(500);
        }

        System.out.println(t.getName() + "状态:"+t.getState());

    }
}

class T extends Thread{

    @Override
    public void run() {
       try {
           int count  = 0;
           while (true){
               count ++;
               for (int i = 0; i < 10; i++) {
                   System.out.println("H"+i + Thread.currentThread().getName());
               }
               Thread.sleep(2000);
               if(count == 10){
                   break;
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}