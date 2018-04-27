package com.UIChart;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
    static Socket s;
    static Socket[] soc;
    static String[] name;
    static int k = 5,i =0,j;
    public static void main(String[] args){

        Server ser = new Server();
        try{
            ServerSocket ss = new ServerSocket(3000);


            soc = new Socket[k];
            name = new String[k];
            while(true){
                s = ss.accept();
                soc[i]= s;
                j=i;
                i++;
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                DataInputStream dis = new DataInputStream(s.getInputStream());
                name[j] = dis.readUTF();
                System.out.println(name[j]+"已进入群聊！");
                dos.writeUTF("欢迎你，"+name[j]);
                new Ser1().start();

            }
        }catch(ConnectException e){
            System.out.println("连接异常！！");

        }catch(IOException e){
            e.printStackTrace();
        }

    }



}

