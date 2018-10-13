package design.observer.step3;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    Frame() {
        frame = new JFrame();

        JPanel content = (JPanel) frame.getContentPane();
        content.setLayout(null);
//        frame.setContentPane(content);
//        frame.setContentPane(new JPanel() {
//            @Override
//            public void paint(Graphics g) {
//                super.paint(g);
//
//            }
//        });

        //设置窗体的位置及大小
        frame.setBounds(0, 0, 1500, 1800);
//        frame.setUndecorated(true);//去掉窗口的装饰

//        frame.setAlwaysOnTop(true);//窗体置顶
//        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });

        final JList wordList;

        String[] words = {"广东", "湖南", "湖北", "广西", "四川", "黑龙江",
                "河北", "甘肃", "宁夏", "辽宁", "吉林", "上海", "重庆", "北京", "河南"};
        //文本框
        final JTextField textField;
        textField = new JTextField();


        //以列表模型构建框
        wordList = new JList(words);
//        wordList.setVisibleRowCount(4);//默认列表构件可以显示8个选项，调整为4个
        JPanel listPanel = new JPanel();
        wordList.setBounds(30, 0, 300, 300);
        JScrollPane jScrollPane = new JScrollPane(wordList);
//        listPanel.add(... JScrollPane(wordList));//将列表插入滚动条
//        jScrollPane.setBounds(30, 0, 300, 300);

        listPanel.add(jScrollPane)
        ;

        listPanel.setBounds(30, 0, 300, 300);

        //列表框默认可以选择多个选项，方法是：按住CTRL健，在要选择的选项上单击。
        //要连续选择选项，必须选择第一个选项，然后按住SHIFT键，在最后一个选项上单击。
        //以下这个设置只能选择一个选项。
        //wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
//        wordList.setLayoutOrientation(JList.VERTICAL);//列表按照水平方向摆放


     /*   JButton button = new JButton("ddddd");
        button.setBounds(10, 10, 200, 300);*/

        //添加列表框到窗体
//        content.add(listPanel);

        content.add(wordList)
                ;

        textField.setBounds(300, 300, 100, 200);
        //添加文本框到窗体
        content.add(textField);


//        content.add(button)


        ;

//        content.add(wordList)
        ;
        //添加列表选择监听器
        wordList.addListSelectionListener(new ListSelectionListener() {
            //实现它的方法
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                StringBuilder str = new StringBuilder("您好,");

                //得到列表框中选定的值
                Object valueList[] = wordList.getSelectedValues();

                for (int i = 0; i < valueList.length; i++) {
                    String s = (String) valueList[i];
                    str.append(s);
                }
                str.append(" 欢迎您！");
                //将选定的值显示在文本框
                textField.setText(str.toString());
            }
        });


        frame.setVisible(true);
    }


    private void resize() {
        System.out.println("resize");

        for (ResizeListener mouseListener : mouseListeners) {
            mouseListener.resize();
        }
    }

    private List<ResizeListener> mouseListeners = new ArrayList<>();

    public void addMouseListener(ResizeListener listener) {
        mouseListeners.add(listener);
    }


}
