package com.chatroom.BestChart;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread implements Runnable{

    Socket socket;

    public WriteThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //写数据
        Scanner sc = new Scanner(System.in);
        try {
            OutputStream os = socket.getOutputStream();

            while(true){
                System.out.println("请输入要发送的数据======================");
                String text = sc.next();
                os.write(text.getBytes());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
