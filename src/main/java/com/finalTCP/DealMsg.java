package com.finalTCP;


import com.alibaba.druid.support.json.JSONUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;
public class DealMsg extends MessageToMessageDecoder<ByteBuf> {


    private final Charset charset;

    public DealMsg() {
        this(Charset.defaultCharset());
    }

    public DealMsg(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        } else {
            this.charset = charset;
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        ByteBuf copy = in.copy(2, in.readableBytes() - 2);
        out.add(copy.toString(this.charset));

    }
}
