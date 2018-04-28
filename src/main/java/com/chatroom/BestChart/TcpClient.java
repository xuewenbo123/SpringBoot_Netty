package com.chatroom.BestChart;

import java.io.IOException;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.184.1", 6666);

        //创建读取线程
        Thread readThread = new Thread(new ReadThread(socket));
        Thread writeThread = new Thread(new WriteThread(socket));

        readThread.start();
        writeThread.start();

    }

}
