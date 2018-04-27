package com.chatroom.simpleChat;

import java.io.InputStream;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) {
        try {
            //1，创建客户端Socket对象,（指定要连接的服务器地址与端口号）
            Socket s = new Socket("192.168.108.1", 8888);
            //2,获取服务器端的反馈回来的信息
            InputStream in = s.getInputStream();
            //获取获取流中的数据
            byte[] buffer = new byte[1024];
            //把流中的数据存储到数组中，并记录读取字节的个数
            int length = in.read(buffer);
            //显示数据
            System.out.println( new String(buffer, 0 , length) );
            //3,关闭流资源
            in.close();
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
