package tank;

/**
 * @author Gzy
 * @version 1.0
 */
public class Bullet extends Thread{
    private int x;
    private int y;
    private int direction;
    private int speed = 5;
    private boolean loop = true;

    public Bullet(int x, int y, int direction, boolean loop) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop){
            try {
                if(this.direction == 0){
                    if(!this.moveUp()){
                        this.loop = false;
                    }
                }
                if(this.direction == 1){
                   if(!this.moveRight()){
                       this.loop = false;
                   }
                }
                if(this.direction == 2){
                    if(!this.moveDown()){
                        this.loop = false;
                    }
                }
                if(this.direction == 3){
                    if (!this.moveLeft()){
                        this.loop = false;
                    }
                }
                Thread.sleep(50);
//                System.out.println("子弹"+Thread.currentThread().getName()+"正在飞..");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        System.out.println("子弹"+Thread.currentThread().getName()+"消失.....");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean moveUp(){
       if(y >= speed){//判断是否到达边界--用于删除子弹对象
           y -= speed;
           return true;
       }else{
           return false;
       }
    }
    public boolean moveDown(){
        if(y + speed <= JPanelTK.WINDOW_HEIGHT ){//判断是否到达边界--用于删除子弹对象
            y += speed;
            return true;
        }else{
            return false;
        }
    }
    public boolean moveRight(){
        if(x + speed <= JPanelTK.WINDOW_WIDTH ){//判断是否到达边界--用于删除子弹对象
            x += speed;
            return true;
        }else{
            return false;
        }
    }
    public boolean moveLeft(){
        if(x - speed >= 0 ){//判断是否到达边界--用于删除子弹对象
            x -= speed;
            return true;
        }else{
            return false;
        }
    }

}
