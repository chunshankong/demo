package observer.step1;

import javax.swing.*;

/**
 * 弹窗器
 * @author yangsiguo
 * @date 2018/8/10 15:11
 */
public class Alerter {


    public void alert(){
        JOptionPane.showMessageDialog(null, "Frame被鼠标点击", "弹窗器", JOptionPane.INFORMATION_MESSAGE);
    }

}
