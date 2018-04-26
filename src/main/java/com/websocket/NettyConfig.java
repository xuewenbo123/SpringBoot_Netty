package com.websocket;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyConfig {

    //netty全局配置类

    //存储每一个客户端进来时的channel对象
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);




}
