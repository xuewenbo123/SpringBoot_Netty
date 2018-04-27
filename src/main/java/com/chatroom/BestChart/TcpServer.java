package com.chatroom.BestChart;

import com.sun.corba.se.pept.transport.ReaderThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        //一个客户端建立通讯只需要一次accept
        Socket socket = serverSocket.accept();
        //创建读写线程
        Thread readThread = new Thread(new ReadThread(socket));
        Thread writeThread = new Thread(new WriteThread(socket));

        readThread.start();
        writeThread.start();


    }

}
