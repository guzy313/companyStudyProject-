package tank;

import javax.swing.*;
import java.awt.*;

public class JFrameTK extends JFrame {
    private JPanelTK jp;

    //框架构造器
    public JFrameTK() throws HeadlessException {
        //初始化面板
        jp = new JPanelTK(new Hero(900,800,0,0));

        Thread thread_jp = new Thread(jp);//jp线程
        thread_jp.start();

        jp.setBackground(Color.BLACK);
        jp.setEnemise();//初始化敌人

        this.add(jp);//面板放入框架
        this.setSize(JPanelTK.WINDOW_WIDTH,JPanelTK.WINDOW_HEIGHT);
        this.addKeyListener(jp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
