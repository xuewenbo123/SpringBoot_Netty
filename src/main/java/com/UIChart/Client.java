package com.UIChart;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

    String name;
    Socket s;
    UI ui;

    //构造方法 ，把UI对象传过来
    public Client(UI ui) {
        this.ui = ui;
    }

    //从登陆界面获得名字并传去服务端
    public void getName(String name) {
        try {
            s = new Socket("127.0.0.1", 3000);
            Cli1 d = new Cli1(s, ui);
            d.start();
            this.name = name;
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从聊天界面获得要发送的内容并经服务器转发给各个客户端
    public void say(String content) {
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

