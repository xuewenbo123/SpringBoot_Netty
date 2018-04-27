package com.UIChart;

import java.io.DataInputStream;
import java.net.Socket;

//输入和输出
class Cli1 extends Thread {
    UI ui;
    Socket s ;
    public Cli1(Socket s,UI ui){
        this.s = s;
        this.ui=ui;
    }
    public void run(){
        try{
            while(true){

                DataInputStream dis = new DataInputStream(s.getInputStream());
                String content = dis.readUTF();
                if(!content.equals("")&&content!=null){
                    System.out.println(content);
                    ui.say(content);
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
