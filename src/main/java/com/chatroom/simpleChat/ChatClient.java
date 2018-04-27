package com.chatroom.simpleChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {

        try {
            //客户端启动Socket，向服务器端发送连接请求
            Socket socket = new Socket("192.168.168.1", 30001);
            BufferedReader message;
            PrintWriter toServer;
            BufferedReader fromServer;
            while(true)
            {
                message=new BufferedReader(new InputStreamReader(System.in));
                toServer=new PrintWriter(socket.getOutputStream());
//          fromServer=new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String readline=message.readLine();
                while(!readline.toLowerCase().equals("end")&&!readline.toLowerCase().equals("exit"))
                {
                    //发送数据是这样的
                    toServer.println(readline);
                    toServer.flush();
                    readline=message.readLine(); //从系统标准输入读入一字符串
                }

                if(readline.equals("exit"))
                    break;
            }

            message.close();
            toServer.close();
            System.out.println("断开连接");


        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("客户端发送连接请求失败---");
        }

    }

}
