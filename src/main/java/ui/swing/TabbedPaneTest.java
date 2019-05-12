
package ui.swing;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.event.ChangeEvent;

import javax.swing.event.ChangeListener;





public class TabbedPaneTest {
    static {

        try {
//            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );

            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

//            UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel" );

//            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {

        // TODO Auto-generated method stub

        TabbedPaneFrame frame = new TabbedPaneFrame();

        frame.setTitle("TabbedPaneFrame");

        frame.setSize(400, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }



}



class TabbedPaneFrame extends JFrame {



    private JTabbedPane tabbedPane;

    private int count = 0;



    public TabbedPaneFrame() {



        // 添加选项卡

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Mercury", null);

        tabbedPane.addTab("Venus", null);

        tabbedPane.addTab("Earth", null);

        tabbedPane.addTab("Mars", null);

        tabbedPane.addTab("Jupiter", null);

        tabbedPane.addTab("Saturn", null);

        tabbedPane.addTab("Uranus", null);

        tabbedPane.addTab("Neptune", null);

        tabbedPane.addTab("Pluto", null);

        // 添加选项卡面板

        add(tabbedPane, "Center");

        // 添加监听器

        tabbedPane.addChangeListener(new ChangeListener() {



            @Override

            public void stateChanged(ChangeEvent e) {

                // TODO Auto-generated method stub

                int n = tabbedPane.getSelectedIndex();

                loadTab(n);

            }

        });

        loadTab(0);

        //添加单选按钮，用于调节选项卡的布局方式

        JPanel buttonPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton wrapButton = new JRadioButton("Wrap tabs");

        wrapButton.setSelected(true);

        wrapButton.addActionListener(new ActionListener() {



            @Override

            public void actionPerformed(ActionEvent arg0) {

                // TODO Auto-generated method stub

                tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

            }

        });

        buttonGroup.add(wrapButton);

        buttonPanel.add(wrapButton);



        JRadioButton scroButton = new JRadioButton("Scroll tabs");

        scroButton.addActionListener(new ActionListener() {



            @Override

            public void actionPerformed(ActionEvent arg0) {

                // TODO Auto-generated method stub

                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

            }

        });

        buttonGroup.add(scroButton);

        buttonPanel.add(scroButton);



        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void loadTab(int n) {

        String title = tabbedPane.getTitleAt(n);

        String countString = String.valueOf(count ++);

        String msg = "this is " + title + ", load at " + countString + " times";

        tabbedPane.setComponentAt(n, new JLabel(msg));

    }

}