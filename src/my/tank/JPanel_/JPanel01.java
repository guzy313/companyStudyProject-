package my.tank.JPanel_;

import my.tank.entity.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JPanel01 extends JPanel implements KeyListener {
    private Hero hero = new Hero(10,10,0,0);//初始化玩家
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tank(g,hero.getX(),hero.getY(),hero.getDirection(),hero.getType());


    }

    /**
     *
     * @param g 画笔
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param direction 坦克方向 0 上 1右 2下 3左
     * @param type 坦克类型 0玩家 -1 AI
     */
    private void tank(Graphics g,int x,int y,int direction,int type){
        switch (type){
            case 0 :
                g.setColor(Color.cyan);
                break;
            case -1:
                g.setColor(Color.yellow);
                break;
                default:
                    g.setColor(Color.yellow);
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
                g.drawLine(x + 20,y + 20,x + 20,y + 50);
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



    //有字符输出时,该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当某个键按下,该方法触发
    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase((char)e.getKeyCode());
        System.out.println(key);
        int moveNum = 1;//位移像素
        switch (key){
            case 'w':
                hero.moveUp();
                hero.setDirection(0);
                break;
            case 'd':
                hero.moveRight();
                hero.setDirection(1);
                break;
            case 's':
                hero.moveDown();
                hero.setDirection(2);
                break;
            case 'a':
                hero.moveLeft();
                hero.setDirection(3);
                break;
                default:
        }
        this.repaint();

    }

    //当某个键松开,该方法触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
