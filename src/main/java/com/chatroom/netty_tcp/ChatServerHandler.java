package com.chatroom.netty_tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    //工程异常时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    //客户端与服务端创建连接时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
    }


    //客户端与服务端断开连接时候
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
    }


    //服务端接收客户端发送数据后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    //服务端处理客户端请求的核心方法
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {


    }



}
