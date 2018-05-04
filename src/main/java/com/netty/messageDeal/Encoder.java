package com.netty.messageDeal;

import com.alibaba.druid.support.json.JSONUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static org.apache.tomcat.util.buf.ByteChunk.convertToBytes;


public class Encoder extends MessageToByteEncoder{

    //自动义编码器

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        String s = JSONUtils.toJSONString(msg);
        byte[] body =  convertToBytes(s);
        int dataLength = body.length;  //读取消息的长度
        out.writeInt(dataLength);  //先将消息长度写入，也就是消息头
        out.writeBytes(body);  //消息体中包含我们要发送的数据
    }

}
