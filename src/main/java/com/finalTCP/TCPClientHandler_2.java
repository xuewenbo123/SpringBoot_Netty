package com.finalTCP;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TCPClientHandler_2 extends SimpleChannelInboundHandler {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        System.out.println("------------    "+o);

    }
}
