package com.chatroom.BestChart;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadThread implements Runnable{

    Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while(true){
                len = is.read(bytes);
                String ip = socket.getInetAddress().getHostName();
                System.out.println("读取数据："+ip+" 消息:  "+new String(bytes,0,len));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
