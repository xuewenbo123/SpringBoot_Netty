package com.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//程序入口，负责启动应用
public class Main {
    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new MyWebSocketChannelHandler());
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            System.out.println("服务端开始等待客户端连接----------------------");
            Channel ch = b.bind(8888).sync().channel();
            ch.closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }




    }


}
