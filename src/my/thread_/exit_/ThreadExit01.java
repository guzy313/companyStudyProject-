package my.thread_.exit_;


public class ThreadExit01 {
    public static void main(String[] args) throws Exception{
        TTT ttt = new TTT();
        ttt.start();
        System.out.println("主线程休眠10秒");
        Thread.sleep(10000);

        ttt.setLoop(false);

    }
}
class TTT extends Thread{
    private boolean loop = true;

    @Override
    public void run() {
        int count = 0;
        while (loop){
            count ++;
            try {
                Thread.sleep(50);
            }catch (Exception e){

            }
            System.out.println("线程运行中"+count);
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
