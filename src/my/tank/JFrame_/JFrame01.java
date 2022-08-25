package my.tank.JFrame_;

import my.tank.JPanel_.JPanel01;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"all"})
public class JFrame01 extends JFrame {
    private JPanel01 jp;

    public JFrame01() {
        jp = new JPanel01();
        jp.setBackground(Color.BLACK);//设置画板背景为黑
        this.add(jp);
        this.setSize(1200,800);
        this.addKeyListener(jp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
