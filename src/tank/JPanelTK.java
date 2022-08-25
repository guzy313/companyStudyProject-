package tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class JPanelTK extends JPanel implements KeyListener,Runnable{
    private Hero hero ;
    private Vector<Enemy> enemise = new Vector<>();//初始化敌人
    private boolean loop = true;//线程开关
    public static int WINDOW_WIDTH = 1800;//面板宽度 --用于计算边界(作用于子弹消失、坦克移动限制等)
    public static int WINDOW_HEIGHT = 1000;//面板高度 --用于计算边界(作用于子弹消失、坦克移动限制等)
    private Vector<Boom> booms = new Vector<>();


    public JPanelTK(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制爆炸效果
//        Image image = Toolkit.getDefaultToolkit().getImage("D:\\学习\\IdeaProjects\\Study\\out\\production\\Study\\boom.gif");
//        g.drawImage(image,500,600,60,60,this);
        if(booms.size() > 0){
            Image image = Toolkit.getDefaultToolkit().getImage("G:\\学习\\untitled\\out\\production\\untitled\\boom.gif");
            for (Boom boom:booms) {
                if(boom.isAlive()){
                    g.drawImage(image,boom.getX(),boom.getY(),60,60,this);
                    boom.lifesDown();
                }else{
                    booms.remove(boom);
                }
            }
        }


        //生成玩家
        if(hero != null){
            drawTank(g,hero.getX(),hero.getY(),hero.getDirection(),hero.getType());
            if(hero.getBullets().size() > 0){
                for (Bullet bullet:hero.getBullets()) {
                    if(bullet.isLoop()){//判断子弹线程是否结束 --未结束(绘制)
                        drawBullet(g,bullet.getX(),bullet.getY(),bullet.getDirection());
                    }else{//结束(删除子弹对象 停止绘制)
                        hero.removeBullet(bullet);
                    }

                }
            }
        }else {
            g.setFont(new Font("宋体",Font.BOLD,100));
            g.drawString("你输了",JPanelTK.WINDOW_WIDTH / 5 * 2 ,JPanelTK.WINDOW_HEIGHT/2);
        }
        //生成敌人
        for (Enemy en:this.getEnemise()) {
            drawTank(g,en.getX(),en.getY(),en.getDirection(),en.getType());
            for (Bullet bullet:en.getBullets()) {//绘制敌方坦克子弹
                if(bullet.isLoop()){//判断子弹线程是否结束 --未结束(绘制)
                    drawBullet(g,bullet.getX(),bullet.getY(),bullet.getDirection());
                }else{//结束(删除子弹对象 停止绘制)
                    en.removeBullet(bullet);
                }
            }
        }


    }

    @Override
    public void run() {

        while (loop){
            try{
                this.repaint();
                if(hero != null){
                    listenHitOnEnemies();//监听玩家子弹是否击中敌方坦克
                    listenHitOnHero();//监听敌方坦克子弹是否击中玩家
                }
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase((char)e.getKeyCode());
        if(hero == null){
            throw new RuntimeException("你已经没了");
        }

        //获取我方坦克的坐标（左上角）
        int h_x = hero.getX();
        int h_y = hero.getY();
        boolean allowMove = true;
        switch (key){
            case 'w':
                for (Enemy enemy:enemise) {//遍历敌方坦克位置--计算碰撞不允许移动情况
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    //敌方坦克向上向下情况(敌方坦克竖着的情况)
                    if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                        //我方坦克在敌方坦克下面试图往上走
                        if(h_x + 40 > e_x  && h_x < e_x +40 && h_y <= e_y + 60 && h_y + 60 > e_y){
                            allowMove = false;
                        }
                    }else{//敌方坦克向左向右情况(敌方坦克横着的情况)
                        if(h_x + 40 > e_x  && h_x < e_x +60 && h_y <= e_y + 40 && h_y + 60 > e_y){
                            allowMove = false;
                        }
                    }
                }
                if(allowMove){
                    hero.moveUp();
                }
                hero.setDirection(0);
                break;
            case 'd':
                for (Enemy enemy:enemise) {//遍历敌方坦克位置--计算碰撞不允许移动情况
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    //敌方坦克向上向下情况(敌方坦克竖着的情况)
                    if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                        //我方坦克在敌方坦克左边试图往右走
                        if(h_y < e_y + 60 && h_y + 40 > e_y && h_x + 60 >= e_x && h_x < e_x + 40 ){
                            allowMove = false;
                        }
                    }else{//敌方坦克向左向右情况(敌方坦克横着的情况)
                        if(h_y < e_y + 40  && h_y + 40 > e_y && h_x + 60 >= e_x && h_x < e_x + 60 ){
                            allowMove = false;
                        }
                    }
                }
                if(allowMove){
                    hero.moveRight();
                }
                hero.setDirection(1);
                break;
            case 's':
                for (Enemy enemy:enemise) {//遍历敌方坦克位置--计算碰撞不允许移动情况
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    //敌方坦克向上向下情况(敌方坦克竖着的情况)
                    if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                        //我方坦克在敌方坦克上面试图往下走
                        if(h_x + 40 > e_x  && h_x < e_x +40 && h_y + 60 >= e_y && h_y + 60 < e_y + 60){
                            allowMove = false;
                        }
                    }else{//敌方坦克向左向右情况(敌方坦克横着的情况)
                        if(h_y + 40 > e_x  && h_x < e_x +60 && h_y + 60 >= e_y && h_y + 60 < e_y + 60){
                            allowMove = false;
                        }
                    }
                }
                if (allowMove){
                    hero.moveDown();
                }
                hero.setDirection(2);
                break;
            case 'a':
                for (Enemy enemy:enemise) {//遍历敌方坦克位置--计算碰撞不允许移动情况
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    //敌方坦克向上向下情况(敌方坦克竖着的情况)
                    if(enemy.getDirection() == 0 || enemy.getDirection() == 2){
                        //我方坦克在敌方坦克左边试图往右走
                        if(h_y < e_y + 60 && h_y + 40 > e_y && h_x <= e_x + 40 && h_x > e_x){
                            allowMove = false;
                        }
                    }else{//敌方坦克向左向右情况(敌方坦克横着的情况)
                        if(h_y < e_y + 40  && h_y + 40 > e_y && h_x <= e_x + 60 && h_x > e_x){
                            allowMove = false;
                        }
                    }
                }
                if(allowMove){
                    hero.moveLeft();
                }
                hero.setDirection(3);
                break;
            default:
        }
        if(key == 'j'){
            hero.shot(hero);//射击
        }

        //动态给敌方坦克传入我方坦克信息
        for (Enemy en:enemise) {
            en.setHero(this.hero);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 绘制坦克 --动态
     * @param g 画笔
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param direction 坦克方向 0 上 1右 2下 3左
     * @param type 坦克类型 0玩家 -1 AI
     */
    private void drawTank(Graphics g,int x,int y,int direction,int type){
        switch (type){
            case 0 :
                g.setColor(Color.yellow);
                break;
            case -1:
                g.setColor(Color.cyan);
                break;
            default:
                g.setColor(Color.cyan);
        }

        switch (direction){
            case 0://向上
                //坦克左边
                g.fill3DRect(x,y,10,60,false);
                //坦克中间
                g.fill3DRect(x + 10,y + 10,20,40,false);
                //坦克右边
                g.fill3DRect(x + 30,y,10,60,false);
                //坦克中间圆形
                g.fillOval(x + 10,y + 20,20,20);
                //坦克炮弹管
                g.drawLine(x + 20,y,x + 20,y + 30);
                break;
            case 1://向右
                //坦克左边
                g.fill3DRect(x,y,60,10,false);
                //坦克中间
                g.fill3DRect(x + 10,y + 10,40,20,false);
                //坦克右边
                g.fill3DRect(x,y + 30,60,10,false);
                //坦克中间圆形
                g.fillOval(x + 20,y + 10,20,20);
                //坦克炮弹管
                g.drawLine(x + 30 ,y + 20,x + 60,y + 20);
                break;
            case 2://向下
                //坦克左边
                g.fill3DRect(x,y,10,60,false);
                //坦克中间
                g.fill3DRect(x + 10,y + 10,20,40,false);
                //坦克右边
                g.fill3DRect(x + 30,y,10,60,false);
                //坦克中间圆形
                g.fillOval(x + 10,y + 20,20,20);
                //坦克炮弹管
                g.drawLine(x + 20,y + 30,x + 20,y + 60);
                break;
            case 3://向左
                //坦克左边
                g.fill3DRect(x,y,60,10,false);
                //坦克中间
                g.fill3DRect(x + 10,y + 10,40,20,false);
                //坦克右边
                g.fill3DRect(x,y + 30,60,10,false);
                //坦克中间圆形
                g.fillOval(x + 20,y + 10,20,20);
                //坦克炮弹管
                g.drawLine(x,y+20,x + 30,y + 20);
                break;

        }
    }

    /**
     * 绘制子弹
     * @return
     */
    private void drawBullet(Graphics g,int x,int y,int direction){
        g.setColor(Color.yellow);
        g.fillOval(x,y,6,6);
    }

    public Vector<Enemy> getEnemise() {
        return enemise;
    }

    public void setEnemise() {
        for (int i = 0; i < 3; i++) {
            this.enemise.add(new Enemy((i + 1) * 100,100,0,-1));
        }
        System.out.println("启动敌方坦克线程");
        for (Enemy en:this.getEnemise()) {
            new Thread(en).start();
        }
    }

    //敌方坦克被击中情况
    public void listenHitOnEnemies(){
        for (Bullet bullet:hero.getBullets()) {//玩家的子弹判断
            for (Enemy enemy:getEnemise()) {
                //上下左右4种情况敌方坦克的边界不同
                if(enemy.getDirection() == 0){
                    int b_x = bullet.getX();
                    int b_y = bullet.getY();
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    if(b_x >= e_x && b_x <= e_x + 40 && b_y >= e_y && b_y <= e_y + 60){
                        enemy.setLoop(false);//停止敌方坦克线程
                        enemise.remove(enemy);//移除敌方坦克单位
                        hero.removeBullet(bullet);//移除我方子弹
                        booms.add(new Boom(enemy.getX(),enemy.getY(),true));
                    }
                }else if(enemy.getDirection() == 1){
                    int b_x = bullet.getX();
                    int b_y = bullet.getY();
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    if(b_x >= e_x && b_x <= e_x + 60 && b_y >= e_y && b_y <= e_y + 40){
                        enemy.setLoop(false);//停止敌方坦克线程
                        enemise.remove(enemy);//移除敌方坦克单位
                        hero.removeBullet(bullet);//移除我方子弹
                        booms.add(new Boom(enemy.getX(),enemy.getY(),true));
                    }
                }else if(enemy.getDirection() == 2){
                    int b_x = bullet.getX();
                    int b_y = bullet.getY();
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    if(b_x >= e_x && b_x <= e_x + 40 && b_y >= e_y && b_y <= e_y + 60){
                        enemy.setLoop(false);//停止敌方坦克线程
                        enemise.remove(enemy);//移除敌方坦克单位
                        hero.removeBullet(bullet);//移除我方子弹
                        booms.add(new Boom(enemy.getX(),enemy.getY(),true));
                    }
                }else if(enemy.getDirection() == 3){
                    int b_x = bullet.getX();
                    int b_y = bullet.getY();
                    int e_x = enemy.getX();
                    int e_y = enemy.getY();
                    if(b_x >= e_x && b_x <= e_x + 60 && b_y >= e_y && b_y <= e_y + 40){
                        enemy.setLoop(false);//停止敌方坦克线程
                        enemise.remove(enemy);//移除敌方坦克单位
                        hero.removeBullet(bullet);//移除我方子弹
                        booms.add(new Boom(enemy.getX(),enemy.getY(),true));
                    }
                }
            }
        }
    }

    //玩家坦克被击中情况
    public void listenHitOnHero(){

        for (Enemy enemy:enemise) {
            for (Bullet bullet:enemy.getBullets()) {//坦克的子弹判断
                    //上下左右4种情况敌方坦克的边界不同
                    if(hero.getDirection() == 0){
                        int b_x = bullet.getX();
                        int b_y = bullet.getY();
                        int h_x = hero.getX();
                        int h_y = hero.getY();
                        if(b_x >= h_x && b_x <= h_x + 40 && b_y >= h_y && b_y <= h_y + 60){
                            booms.add(new Boom(hero.getX(),hero.getY(),true));
                            hero = null;
                        }
                    }else if(hero.getDirection() == 1){
                        int b_x = bullet.getX();
                        int b_y = bullet.getY();
                        int h_x = hero.getX();
                        int h_y = hero.getY();
                        if(b_x >= h_x && b_x <= h_x + 60 && b_y >= h_y && b_y <= h_y + 40){
                            booms.add(new Boom(hero.getX(),hero.getY(),true));
                            hero = null;
                        }
                    }else if(hero.getDirection() == 2){
                        int b_x = bullet.getX();
                        int b_y = bullet.getY();
                        int h_x = hero.getX();
                        int h_y = hero.getY();
                        if(b_x >= h_x && b_x <= h_x + 40 && b_y >= h_y && b_y <= h_y + 60){
                            booms.add(new Boom(hero.getX(),hero.getY(),true));
                            hero = null;
                        }
                    }else if(hero.getDirection() == 3){
                        int b_x = bullet.getX();
                        int b_y = bullet.getY();
                        int h_x = hero.getX();
                        int h_y = hero.getY();
                        if(b_x >= h_x && b_x <= h_x + 60 && b_y >= h_y && b_y <= h_y + 40){
                            booms.add(new Boom(hero.getX(),hero.getY(),true));
                            hero = null;
                        }
                    }
            }
        }

    }

}
