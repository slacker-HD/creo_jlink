package jlinkasync;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.ptc.cipjava.*;
import com.ptc.pfc.pfcSession.*;
import com.ptc.pfc.pfcAsyncConnection.*;

public class JlinkAsync {

    private JFrame jFrame = new JFrame("Jlink异步测试");
    private Container mainContainer = jFrame.getContentPane();

    private JLabel jl_1 = new JLabel("Creo程序路径：");
    private JTextField jf_apppath = new JTextField();

    private JButton btn_new = new JButton("启动新回话");
    private JButton btn_quit = new JButton("结束会话并退出");

    private static Session currSession;
    private AsyncConnection ac;

    public JlinkAsync() {
        jFrame.setBounds(600, 200, 700, 160);
        mainContainer.setLayout(new BorderLayout());// 布局管理器
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        jFrame.setVisible(true);
    }

    public void init() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("Jlink异步测试"));
        mainContainer.add(titlePanel, "North");

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        jl_1.setBounds(50, 20, 120, 20);
        fieldPanel.add(jl_1);
        jf_apppath.setBounds(180, 20, 420, 20);
        jf_apppath.setText("C:\\PTC\\Creo 2.0\\Parametric\\bin\\parametric.exe");
        fieldPanel.add(jf_apppath);
        mainContainer.add(fieldPanel, "Center");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btn_new);
        buttonPanel.add(btn_quit);
        mainContainer.add(buttonPanel, "South");

        btn_new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    ac = pfcAsyncConnection.AsyncConnection_Start(jf_apppath.getText(), null);
                    currSession = ac.GetSession();
                    currSession.UIShowMessageDialog("利用异步程序启动了对话。", null);
                } catch (jxthrowable e) {
                    e.printStackTrace();
                }
            }
        });

        btn_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    ac.End();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        System.loadLibrary("pfcasyncmt");
        new JlinkAsync();
    }

}