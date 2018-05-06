package com.finalTCP;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.Serializable;

public class MsgEncode extends MessageToByteEncoder<CarData>{


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CarData carData, ByteBuf byteBuf) throws Exception {

        byteBuf.writeInt(carData.getA());
        byteBuf.writeInt(carData.getB());
        byte[] bytes = carData.getBody().getBytes();
        byteBuf.writeBytes(bytes);
        byteBuf.writeInt(carData.getC());
        byteBuf.writeInt(carData.getD());
    }
}
