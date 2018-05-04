package com.TCP_netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {

    public ClientHandler(){}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {


        System.out.println("接收服务器响应msg:["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
