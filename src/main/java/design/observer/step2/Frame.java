package observer.step2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangsiguo
 * @date 2018/8/10 14:47
 */
public class Frame {

    JFrame frame;

    Frame(){
        frame  = new JFrame();
        frame.setContentPane(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

            }
        });

        //设置窗体的位置及大小
        frame.setBounds(0, 0, 500, 800);
//        frame.setUndecorated(true);//去掉窗口的装饰
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);//窗体置顶
//        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseClick();
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mouseClick();
            }
        });

    }

    private void mouseClick(){
        System.out.println("resize");

        for (ResizeListener mouseListener : mouseListeners) {
            mouseListener.resize();
        }
    }

    private List<ResizeListener> mouseListeners = new ArrayList<>();

    public void addMouseListener(ResizeListener listener){
        mouseListeners.add(listener);
    }





}
