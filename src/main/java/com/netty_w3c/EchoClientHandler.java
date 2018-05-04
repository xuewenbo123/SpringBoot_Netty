package com.netty_w3c;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final String MESSAGE="Netty is a NIO client server framework which enables quick and easy development of network applications such as protocol servers and clients.";
    public EchoClientHandler(){}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(MESSAGE.getBytes()));
        System.out.println("客户端active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("接收服务器响应msg:["+msg+"]");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("客户端收到服务器响应数据处理完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("客户端异常退出");
    }

}
