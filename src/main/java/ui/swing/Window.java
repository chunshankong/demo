package ui.swing;


import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @author yangsiguo
 * @date 2019/5/16
 * @desc TODO add description in here
 */
public class Window {

    static ImageIcon icon = new ImageIcon("C:\\Users\\yangsiguo\\Desktop\\784393899cfecf873f25a3569099d23b.png");
    static int mx;
    static int my;
    static int jfx;
    static int jfy;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setBounds(500, 500, 300, 300);

//        jFrame.setBackground(new Color(r, g, b, int alph));

//        AWTUtilities.setWindowOpacity(frame,0.6f);
//        AWTUtilities.setWindowShape(frame,new Rectangle());

        JPanel pane = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);

                g.setColor(Color.red);
                g.drawImage(icon.getImage(),10,10,100,100,null);
//                g.drawimage(icon,10, 10, 100, 100, true);
//                super.paintComponent(g);
                g.drawRect(10,10,100,100);
            }
        };
        pane.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(jfx+(e.getXOnScreen()-mx), jfy+(e.getYOnScreen()-my));
            }
        });
        pane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mx=e.getXOnScreen();
                my=e.getYOnScreen();
                jfx=frame.getX();
                jfy=frame.getY();
            }
        });

        JButton button = new JButton("dd");
        pane.add(button);

        frame.setContentPane(pane);

        AWTUtilities.setWindowOpaque(frame, false);

        frame.setVisible(true);
    }
}
