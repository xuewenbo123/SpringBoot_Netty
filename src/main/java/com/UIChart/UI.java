package com.UIChart;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UI{

    //主方法
//    public static void main(String[] args){
//
//        UI ui = new UI();
//        ui.cli = new Client(ui);
//        ui.initFrame();
//        ui.showLogin();
//    }



    Ser1 ser1 = new Ser1();

    //初始化业务对象
    public Client cli = null;
    public void initCli(){

    }

    //初始化主窗口
    public int width = 720;
    public int height = 550;
    public JFrame jFrame = null;  //界面窗口
    public JLayeredPane layeredPane = null;  //层叠容器
    public JPanel backLayer = null;  //背景层
    public JPanel frontLayer = null;  //前景层
    public CardLayout cardLayout = null;  //前景层布局器

    public void initFrame(){
        jFrame = new JFrame("弘安聊天室");
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
        jFrame.add(layeredPane);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backLayer = new JPanel();
        ((FlowLayout)backLayer.getLayout()).setHgap(0);
        ((FlowLayout)backLayer.getLayout()).setVgap(0);
        backLayer.setSize(width,height);
        backLayer.setLocation(0,0);
        JLabel bg = new JLabel(new ImageIcon("12.jpg"));
        backLayer.add(bg);



        layeredPane.add(backLayer,new Integer(0));

        frontLayer = new JPanel();
        cardLayout = new CardLayout(0,0);
        frontLayer.setLayout(cardLayout);
        frontLayer.setOpaque(false);
        frontLayer.setSize(width,height);
        frontLayer.setLocation(0,0);

        layeredPane.add(frontLayer,new Integer(1));

    }

    //登录界面
    public JPanel loginPane = null;
    public JTextField loginCodeInput = null;

    public JLabel loginTipsLabel = null;
    public void showLogin(){
        if(loginPane == null){
            loginPane = new JPanel();
            loginPane.setOpaque(false);

            Box loginBox = Box.createVerticalBox();
            loginBox.add(Box.createVerticalStrut(180));

            JPanel welcome_panel = new JPanel();
            welcome_panel.setOpaque(false);
            JLabel welcome_label = new JLabel("弘安聊天室");

            welcome_label.setForeground(Color.WHITE);
            welcome_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            welcome_panel.add(welcome_label);
            loginBox.add(welcome_panel);

            loginBox.add(Box.createVerticalStrut(50));
            JPanel code_panel = new JPanel();
            code_panel.setOpaque(false);
            JLabel code_label = new JLabel("姓名：");
            code_label.setForeground(Color.WHITE);
            code_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
            code_panel.add(code_label);
            loginCodeInput = new JTextField(10);
            loginCodeInput.setFont(new Font("微软雅黑", Font.PLAIN,25));
            code_panel.add(loginCodeInput);
            loginBox.add(code_panel);

            loginBox.add(Box.createVerticalStrut(30));

            JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton login_btn = new JButton("登 录");
            login_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(login_btn);

            JButton reset_btn = new JButton("重 置");
            reset_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(reset_btn);
            loginBox.add(btn_panel);

            loginBox.add(Box.createVerticalStrut(10));

            JPanel tips_panel = new JPanel();
            tips_panel.setOpaque(false);
            loginTipsLabel = new JLabel("");
            loginTipsLabel.setForeground(new Color(238,32,32));
            loginTipsLabel.setFont(new Font("微软雅黑",Font.PLAIN,20));
            tips_panel.add(loginTipsLabel);
            loginBox.add(tips_panel);

            loginPane.add(loginBox);

            frontLayer.add("loginPane",loginPane);
            cardLayout.show(frontLayer,"loginPane");
            frontLayer.validate();
            loginCodeInput.requestFocus();

            reset_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    loginCodeInput.setText("");
                    loginTipsLabel.setText("");
                    loginCodeInput.requestFocus();
                }
            });

            login_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    String code_str = loginCodeInput.getText();

                    if("".equals(code_str)){
                        loginTipsLabel.setText("姓名不能为空！");
                        loginCodeInput.requestFocus();

                    }else{

                        cli.getName(code_str);

                        showTalk();

                    }
                }
            });
        }else{
            cardLayout.show(frontLayer,"loginPane");
            loginCodeInput.setText("");

            loginTipsLabel.setText("");
            loginCodeInput.requestFocus();
        }
    }




    //聊天主界面
    public JPanel menuPane = null;
    public JTextArea input = null;
    public JTextArea talk  = new JTextArea(25,70);
    public void showTalk(){

        menuPane = new JPanel();
        menuPane.setOpaque(false);
        menuPane.setLayout(new BorderLayout());

        JPanel up = new JPanel();
        Box tipsBox = Box.createVerticalBox();
        menuPane.add(up,BorderLayout.NORTH); //北边顶上
        up.add(tipsBox);

        JLabel tips_label = new JLabel("在线朋友");
        tips_label.setForeground(Color.WHITE);
        tips_label.setFont(new Font("微软雅黑",Font.PLAIN,20));
        tips_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        tipsBox.add(tips_label);
        tipsBox.add(Box.createVerticalStrut(10));
        JLabel upTxt = new JLabel("");  //接收在线朋友(需完善)

        tipsBox.add(upTxt);

        JPanel talk_panel = new JPanel();//中间聊天对话框
        talk_panel.setOpaque(false);

        menuPane.add(talk_panel,BorderLayout.WEST);

        JScrollPane sp = new JScrollPane(talk);
        talk_panel.add(talk);

        Box inputBox = Box.createHorizontalBox();  //下边输入框
        menuPane.add(inputBox,BorderLayout.SOUTH);

        JPanel input_panel = new JPanel();
        input_panel.setOpaque(false); //放置输入框
        input = new JTextArea(4,30);
        input.setFont(new Font("微软雅黑",Font.PLAIN,20));
        input.setAlignmentX(Component.LEFT_ALIGNMENT);
        input_panel.add(input);
        inputBox.add(input_panel);
        inputBox.add(Box.createHorizontalStrut(0));
        JButton send_btn = new JButton("发送");
        send_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
        inputBox.add(send_btn);

        frontLayer.add("menuPane",menuPane);
        cardLayout.show(frontLayer,"menuPane");
        frontLayer.validate();

        send_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String append = talk.getText();
                String content = input.getText();
                talk.setText(append+'\n'+content);
                input.setText("");
                cli.say(content);

            }
        });


    }
    public void say(String content){
        if(talk!=null){
            String append = talk.getText();

            talk.setText(append+'\n'+content);
        }



    }

}
