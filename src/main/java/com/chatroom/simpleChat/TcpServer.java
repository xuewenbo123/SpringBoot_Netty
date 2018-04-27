package com.chatroom.simpleChat;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        OutputStream out = null;
        try{
            //1,创建服务器ServerSocket对象（指定服务器端口号）
            ServerSocket ss = new ServerSocket(8888);
            //2，开启服务器了，等待客户端的连接，当客户端连接后，可以获取到连接服务器的客户端Socket对象
            Socket s = ss.accept();
            //3,给客户端反馈信息
            /*
             * a,获取客户端的输出流
             * b,在服务端端，通过客户端的输出流写数据给客户端
             */
            //a,获取客户端的输出流
            out = s.getOutputStream();
            //b,在服务端端，通过客户端的输出流写数据给客户端
            out.write("你已经连接上了服务器".getBytes());
            //4,关闭流资源
            out.close();
            ss.close();  //服务器流 通常都是不关闭的
        }catch (Exception e){

        }finally {


        }



}

}
