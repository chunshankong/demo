package observer.step1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
/*        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseClick();
            }
        });*/
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });


    }

 /*   private void mouseClick(){
        System.out.println("resize");
        amplifier.mouseClick();
    }*/
    private void resize(){
        System.out.println("resize");
        amplifier.mouseClick();
        shader.mouseClick();
    }


    Amplifier amplifier ;
    public void addAmplifier(Amplifier amplifier){
        this.amplifier = amplifier;
    }

    Shader shader;
    public void addShader(Shader shader){
        this.shader = shader;
    }



}
