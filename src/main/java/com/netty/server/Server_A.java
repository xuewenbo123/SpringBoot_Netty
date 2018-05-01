package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server_A {

    public void bind(int port) throws Exception{
        //接收客户端连接用
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        //处理网络读写事件
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            //配置服务器启动类
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))//配置日志输出
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf byteBuf = Unpooled.copiedBuffer("&".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));//设置分隔符解码器
//                            ch.pipeline().addLast(new FixedLengthFrameDecoder(30));//设置定长解码器 长度设置为30
                            //                            LineBasedFrameDecoder      换行符解码器
                            ch.pipeline().addLast(new StringDecoder());//设置字符串解码器 自动将报文转为字符串
                            ch.pipeline().addLast(new Serverhandler());//处理网络IO 处理器
                        }
                    });
            //绑定端口 等待绑定成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务器退出
            f.channel().closeFuture().sync();
        }finally{
            //释放线程资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        int port=8000;
        new Server_A().bind(port);
    }

}
