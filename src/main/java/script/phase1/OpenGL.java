package script.phase1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

/**
 * @author yangsiguo
 */
public class OpenGL {

    static Color color1;

    public static void fillRect(int x, int y, int width, int height, String color) {
        color1 = Color.WHITE;
        switch (color) {
            case "red":
                color1 = Color.RED;
                break;
            case "blue":
                color1 = Color.BLUE;
                break;
            case "yellow":
                color1 = Color.YELLOW;
                break;
            case "black":
                color1 = Color.BLACK;
                break;
            case "gray":
                color1 = Color.GRAY;
                break;
            case "green":
                color1 = Color.GREEN;
                break;
        }
        JFrame frame = new JFrame();
        frame.setContentPane(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(color1);
                g.fillRect(x - x, y - y, width, height);
            }
        });

        //设置窗体的位置及大小
        frame.setBounds(x, y, width, height);
        frame.setUndecorated(true);//去掉窗口的装饰
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);//窗体置顶
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

    }
}
