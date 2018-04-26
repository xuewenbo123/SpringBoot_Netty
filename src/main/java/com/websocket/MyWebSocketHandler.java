package com.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import javax.xml.soap.Text;
import java.util.Date;


//接收/请求/响应客户端websocket请求的核心业务处理类
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handleShake;

    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";


    //客户端与服务端创建连接时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端链接开启！！----------channelActive");
    }


    //客户端与服务端断开连接时候调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端断开连接！！--------channelInactive");
    }


    //服务端接收客户端发送过来的数据之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    //工程异常时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    //服务端处理客户端websocket请求的核心方法
    @Override
    protected void messageReceived(ChannelHandlerContext context, Object o) throws Exception {

        if(o instanceof FullHttpRequest){
            //处理客户端向服务端发送http请求的业务
            handleHttpRequest(context,(FullHttpRequest)o);

        }else if(o instanceof WebSocketFrame){
            //处理websocket链接业务
            handleWebSocketFrame(context,(WebSocketFrame) o);
        }

    }

    //自定义方法：处理客户端与服务端之间的websocket业务
    private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        //判断是否是关闭websocket的指令
        if(frame instanceof CloseWebSocketFrame){
            handleShake.close(ctx.channel(),(CloseWebSocketFrame) frame.retain());
        }
        //判断是否是ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是二进制消息
        if(!(frame instanceof TextWebSocketFrame)){
            System.out.println("目前不支持二进制消息！——————————");
            throw new RuntimeException(this.getClass().getName()+"----不支持二进制消息");
        }

        //返回应答消息
        //获取客户端向服务端发送的消息
        String text = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到客户端的消息======"+text);
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "=============" + text);

        //服务端向已经链接的客户端发送信息: 群发
        NettyConfig.group.writeAndFlush(tws);


    }


    //自定义方法：处理客户端向服务端发送http请求的业务
    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){

        if(!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
        handleShake = wsFactory.newHandshaker(req);
        if(handleShake == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handleShake.handshake(ctx.channel(),req);
        }

    }

    //自定义方法：服务端向客户端响应消息
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res){

        if(res.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);

        if(res.status().code() != 200){
            //关闭连接
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


}
