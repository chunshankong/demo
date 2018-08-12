package design.observer.step1;

import design.observer.step2.ResizeListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 放大器
 * @author yangsiguo
 * @date 2018/8/10 16:34
 */
public class Amplifier  {

    JFrame frame;
    int x, y, width, height;

    public Amplifier() {

        frame = new JFrame();
        x = 800;
        y = 0;
        width = 200;
        height = 200;
        //设置窗体的位置及大小
        frame.setBounds(x, y, width, height);
//        frame.setUndecorated(true);//去掉窗口的装饰
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

    public void mouseClick() {
        frame.setSize(width += 1, height += 1);
        frame.repaint();
    }


}
