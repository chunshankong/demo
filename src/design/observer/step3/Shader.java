package design.observer.step3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 变色器
 * @author yangsiguo
 * @date 2018/8/10 15:12
 */
public class Shader implements ResizeListener {

    JFrame frame;
    static Color color1;

    Shader(){
        color1 = Color.WHITE;

        frame = new JFrame();
        frame.setContentPane(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(color1);
                g.fillRect(0, 0, 200, 200);
            }
        });

        //设置窗体的位置及大小
        frame.setBounds(500, 0, 200, 200);
        frame.setUndecorated(true);//去掉窗口的装饰
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);//窗体置顶
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });

    }

    public void mouseClick(){

        int color = (int)(1+Math.random()*(6-1+1));
        switch (color) {
            case 1:
                color1 = Color.RED;
                break;
            case 2:
                color1 = Color.BLUE;
                break;
            case 3:
                color1 = Color.YELLOW;
                break;
            case 4:
                color1 = Color.BLACK;
                break;
            case 5:
                color1 = Color.GRAY;
                break;
            case 6:
                color1 = Color.GREEN;
                break;
                default:break;
        }
        this.frame.repaint();
    }


    @Override
    public void resize() {
        mouseClick();
    }
}
