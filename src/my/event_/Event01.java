package my.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Event01 extends JFrame{
    private Mp01 mp01 = null;
    public static void main(String[] args){
        Event01 event01 = new Event01();

    }

    public Event01() throws HeadlessException {
        mp01 = new Mp01();
        mp01.setBackground(Color.BLACK);
        this.add(mp01);
        this.setSize(500,500);
        this.addKeyListener(mp01);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}

class Mp01 extends JPanel implements KeyListener{
    private int x = 10;
    private int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.yellow);
        g.fillOval(x,y,20,20);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char k = Character.toLowerCase((char)e.getKeyCode());//转小写

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y --;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y ++;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x --;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x ++;
        }

        /*
        switch (k){//aswd控制方向
            case 'w':
                y --;
                break;
            case 'a':
                x --;
                break;
            case 's':
                y ++;
                break;
            case 'd':
                x ++;
                break;
                default:
        }*/
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
