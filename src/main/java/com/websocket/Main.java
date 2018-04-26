package com.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

//程序入口，负责启动应用
public class Main {
    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }




    }


}
