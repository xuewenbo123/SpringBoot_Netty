package com.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class Serverhandler extends ChannelHandlerAdapter {

    private static final String MESSAGE="It greatly simplifies and streamlines network programming such as TCP and UDP socket server.";
//

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("接收客户端msg:["+msg+"]");
        ByteBuf echo= Unpooled.copiedBuffer(MESSAGE.getBytes());
        ChannelFuture channelFuture = ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
