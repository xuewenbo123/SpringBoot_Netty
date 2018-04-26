package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server_A {

    public static void main(String[] args) {

    }

    public void bind(int port){
        //配置服务端nio线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup  = new NioEventLoopGroup();



    }


}
