package com.TCPDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class TCPClient {

    public static String HOST = "127.0.0.1";
    public static int PORT = 9999;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(HOST, PORT);

    /**
     * 初始化Bootstrap
     */
    public static final Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new TCPClientChannelHandler());
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public static final Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            System.out.println("连接Server失败,host: " +host+"---port:  "+port);
            e.printStackTrace();
            return null;
        }
        return channel;
    }

    public static void sendMsg(String msg) throws Exception {
        if (channel != null) {
            channel.writeAndFlush(msg).sync();
        } else {
            System.out.println("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            long t0 = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                sendMsg(i + "你好1");
            }
            long t1 = System.nanoTime();
            System.out.println("发送使用时间：  "+(t1-t0));
        } catch (Exception e) {
            System.out.println("TCPClient main err");
            e.printStackTrace();
        }
    }

}
