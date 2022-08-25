package tank;

import java.util.Vector;

public class Hero extends Tank implements Runnable{
    private Vector<Bullet> bullets = new Vector<>();
    public Hero(int x, int y, int direction, int type) {
        super(x, y, direction, type);
        this.setSPEED(20);//设置玩家速度
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
            py_y = 20 - 3;//+6 子弹像素偏移量
        }else if(this.getDirection() == 2){
            py_x = 20 - 3;//-3 子弹像素偏移量
            py_y = 60;
        }else if(this.getDirection() == 3){
            py_x = 0;//炮管最左边所以无偏移
            py_y = 20 - 3;
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

    @Override
    public void run() {
        /*
        //坦克对象起线程，单线程跑子弹
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
              System.out.println("还在运行的子弹数量:"+bullets.size());
          }catch (Exception e){
              e.printStackTrace();
          }
       }*/
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void shot(Hero hero){
        hero.newBullet().start();
    }
}
