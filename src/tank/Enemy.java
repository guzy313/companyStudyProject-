package tank;

import java.util.Vector;

public class Enemy extends Tank implements Runnable{
    private Vector<Bullet> bullets = new Vector<>();
    private boolean loop = true;
    private Hero hero;//用于记录当前我方坦克信息

    public Enemy(int x, int y, int direction, int type) {
        super(x, y, direction, type);
        this.setSPEED(10);
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    @Override
    public void run() {
        while (loop){
            try {
                Thread.sleep(((int)(Math.random() * 100 + 1) / 10) * 1000 );//1-10秒随机发射
                randomMoveTimes(this);//方向随机以及随机移动多少次
                shot(this);//随机发射子弹

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /*
        while (true){
            try {
                if(bullets.size() > 0){
                    for (Bullet bullet:bullets) {//多个子弹不同方向发射
                        if(bullet.getDirection() == 0){
                            if(!bullet.moveUp()){
                                this.removeBullet(bullet);
                            }
                        }else if(bullet.getDirection() == 1){
                            if(!bullet.moveRight()){
                                this.removeBullet(bullet);
                            }
                        }else if(bullet.getDirection() == 2){
                            if(!bullet.moveDown()){
                                this.removeBullet(bullet);
                            }
                        }else if(bullet.getDirection() == 3){
                            if(!bullet.moveLeft()){
                                this.removeBullet(bullet);
                            }
                        }
                    }
                }
                Thread.sleep(1000);
                System.out.println("敌方还在运行的子弹数量:"+bullets.size());
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
    }

    public void randomMoveTimes(Enemy enemy){
        try {
            int randomDirection = (int)(Math.random() * 4) ;//随机坦克方向
            for (int i = 0; i < (int)(Math.random() * 10); i++) {//随机移动0 - 9步
                switch (randomDirection){//
                    case 0:
                        enemy.setDirection(0);
                        enemy.moveUp();
                        break;
                    case 1:
                        enemy.setDirection(1);
                        enemy.moveRight();
                        break;
                    case 2:
                        enemy.setDirection(2);
                        enemy.moveDown();
                        break;
                    case 3:
                        enemy.setDirection(3);
                        enemy.moveLeft();
                        break;
                    default:
                        break;
                }
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //新建子弹对象-放入子弹数组中(发射子弹)
    public Bullet newBullet(){

        //以下子弹不同方向的发射位置往炮口偏移量--根据绘制时候不同方向坦克的的炮口值与左上角的差设定(固定4个方向)
        //还要考虑子弹的像素--宽6像素 +-3
        int py_x = 0,py_y = 0;
        if(this.getDirection() == 0){
            py_x = 20 - 3;
        }else if(this.getDirection() == 1){
            py_x = 20 + 30;//+30炮管长度
            py_y = 10 + 6;//+6 子弹像素偏移量
        }else if(this.getDirection() == 2){
            py_x = 10 + 6;//+6 子弹像素偏移量
            py_y = 20;
        }else if(this.getDirection() == 3){
            py_x = 0;//炮管最左边所以无偏移
            py_y = 10 + 6;
        }
        Bullet bullet = new Bullet(this.getX() + py_x, this.getY() + py_y, this.getDirection(), true);
        bullets.add(bullet);
        return bullet;
    }

    /**
     * 移除子弹-用于打中墙壁、到达边界释放资源、打中坦克等情况
     * @param bullet
     */
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void shot(Enemy enemy){
        enemy.newBullet().start();
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
