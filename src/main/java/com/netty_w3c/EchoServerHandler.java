package com.netty_w3c;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter=0;
    private static final String MESSAGE="It greatly simplifies and streamlines network programming such as TCP and UDP socket server.";


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       System.out.println("服务器异常退出"+cause.getMessage());
       ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("接收客户端msg:["+msg+"]");
//        ByteBuf echo = Unpooled.copiedBuffer("服务端响应msg:在这里写入".getBytes());
//        ctx.writeAndFlush(echo);

        System.out.println("接收客户端msg:["+msg+"]");
        ByteBuf echo=Unpooled.copiedBuffer(MESSAGE.getBytes());
        ctx.writeAndFlush(echo);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);

        ctx.flush();
        System.out.println("服务器readComplete 响应完成");
    }
}
