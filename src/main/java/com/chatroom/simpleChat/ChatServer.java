package com.chatroom.simpleChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(30001);
            System.out.println("聊天服务等待连接-----------------------");
            long count = 0L;
            while(true){
                Socket socket = server.accept();
                count++;
                System.out.println("服务器接收到客户端--- "+socket.getInetAddress().getHostAddress()+" 服务端链接次数："+count);
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader message=new BufferedReader(new InputStreamReader(System.in));
                String line=fromClient.readLine();
                while(line!=null&&!line.equals("exit")) {
                        System.out.println(socket.getInetAddress().getHostAddress()+"："+line);
                        line=fromClient.readLine();
                 }
                System.out.println(socket.getInetAddress().getHostAddress()+"退出");
                message.close();
                fromClient.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
