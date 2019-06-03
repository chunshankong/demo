package rpa.compensate.window;

import rpa.compensate.CompensateJob;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame {

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

    public static JFrame frame;
    private JPasswordField passwordField;
    private boolean isLogin = false;

    private static JLabel jobDesc,jobCount,totalJobCount;

    private static JButton ebutton,cbutton;

    public static void callJobProcessed(boolean processed){
        if (processed) {
            Frame.jobDesc.setText("处理中");
//            cbutton.setEnabled(false);
//            ebutton.setEnabled(false);
        }else {
            Frame.jobDesc.setText("待处理");
            cbutton.setEnabled(true);
            ebutton.setEnabled(true);
        }
    }

    public static void callJobCount(int jobCount){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame.jobCount.setText(String.valueOf(jobCount));
//                frame.repaint();
            }
        });
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame.jobCount.setText(String.valueOf(jobCount));
//                frame.repaint();
            }
        });
    }

    public static void callTotalJobCount(int totalJobCount){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame.totalJobCount.setText(String.valueOf(totalJobCount));
//                frame.repaint();
            }
        });
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame.totalJobCount.setText(String.valueOf(totalJobCount));
//                frame.repaint();
            }
        });
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        createFrame();
    }

    public static void createFrame()
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame window = new Frame();
                    window.frame.setAlwaysOnTop(true);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the application.
     */
    public Frame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        String userName = "111";
        String userPwd = "111";

        frame = new JFrame();
        frame.setTitle("Robot");
        frame.setBounds(600, 200, 667, 453);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

  /*      JLabel label = new JLabel("账户");
        label.setBounds(116, 49, 50, 23);
        frame.getContentPane().add(label);

        JLabel label_1 = new JLabel("密码：");
        label_1.setBounds(116, 85, 50, 23);
        frame.getContentPane().add(label_1);

        JLabel label_2 = new JLabel("用户状态：");
        label_2.setBounds(433, 49, 100, 23);
        frame.getContentPane().add(label_2);*/

        JLabel label_2_j = new JLabel("任务状态：");
        label_2_j.setBounds(433, 200, 100, 23);
        frame.getContentPane().add(label_2_j);

        jobDesc = new JLabel("待处理");
        jobDesc.setForeground(new Color(255, 0, 0));
        jobDesc.setBounds(499, 200, 40, 23);
        frame.getContentPane().add(jobDesc);

        JLabel label_2_r = new JLabel("本次任务总数：");
        label_2_r.setBounds(433, 230, 100, 23);
        frame.getContentPane().add(label_2_r);

        totalJobCount = new JLabel("0");
        totalJobCount.setForeground(new Color(255, 0, 0));
        totalJobCount.setBounds(520, 230, 40, 23);
        frame.getContentPane().add(totalJobCount);

        JLabel label_3_r = new JLabel("当前任务：");
        label_3_r.setBounds(433, 250, 100, 23);
        frame.getContentPane().add(label_3_r);

        jobCount = new JLabel("0");
        jobCount.setForeground(new Color(255, 0, 0));
        jobCount.setBounds(499, 250, 40, 23);
        frame.getContentPane().add(jobCount);

        ebutton = new JButton("执行代偿修改任务");
        ebutton.setBackground(new Color(255, 255, 255));
        ebutton.setBounds(126, 200, 212, 23);
//        ebutton.setEnabled(false);
//        ebutton.addActionListener(e -> new Thread(CompensateJob::execute).start());

        ebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, Void>() {
                    protected Void doInBackground() {
                        // 执行耗时的写文件任务
                        CompensateJob.execute();
                        return null;
                    }
                    protected void done() {

                    }
                }.execute();
            }
        });

        frame.getContentPane().add(ebutton);

        cbutton = new JButton("点击代偿JOB");
        cbutton.setBackground(new Color(255, 255, 255));
        cbutton.setBounds(126, 250, 212, 23);
//        cbutton.setEnabled(false);
//        cbutton.addActionListener((actionEvent)-> CompensateJob.executeClickJob());
        cbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, Void>() {
                    protected Void doInBackground() {
                        // 执行耗时的写文件任务
                        CompensateJob.executeClickJob();
                        return null;
                    }
                    protected void done() {

                    }
                }.execute();
            }
        });


        frame.getContentPane().add(cbutton);


       /* JLabel label_3 = new JLabel("未登录");
        label_3.setForeground(new Color(255, 0, 0));
        label_3.setBounds(499, 49, 40, 23);
        frame.getContentPane().add(label_3);

        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(172, 49, 166, 23);
        frame.getContentPane().add(formattedTextField);

        passwordField = new JPasswordField();
        passwordField.setBounds(172, 85, 166, 23);
        frame.getContentPane().add(passwordField);

        JButton button = new JButton("login");
        button.setBackground(new Color(255, 255, 255));
        button.setBounds(126, 121, 212, 23);
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String getUserName = formattedTextField.getText();
                String getUserPwd = passwordField.getText();
                if (userName.equals(getUserName) && userPwd.equals(getUserPwd)) {
                    isLogin = true;
                    loginSuccess();
                } else {
                    isLogin = false;
                }
                if (isLogin) {
                    JOptionPane.showMessageDialog(null, "登录成功!", "消息", JOptionPane.PLAIN_MESSAGE);
                    label_3.setText("已登录");
                    label_3.setForeground(Color.BLUE);
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败!", "消息", JOptionPane.WARNING_MESSAGE);
                    label_3.setText("未登录");
                    label_3.setForeground(Color.RED);
                }
            }
        });*/
    }
    private void loginSuccess(){
        cbutton.setEnabled(true);
        ebutton.setEnabled(true);
    }
}