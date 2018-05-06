package com.finalTCP;


import com.alibaba.druid.support.json.JSONUtils;
import com.netty.messageDeal.Decoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.Scanner;


public class TCPClient {

        public void connect(int port,String host)throws Exception{
            //网络事件处理线程组
            EventLoopGroup group=new NioEventLoopGroup();

                //配置客户端启动类
                Bootstrap b=new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)//设置封包 使用一次大数据的写操作，而不是多次小数据的写操作
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("decoder",new DealMsg());//设置自定义解码器
                                ch.pipeline().addLast("encoder",new MsgEncode());
                                ch.pipeline().addLast(new TCPClientHandler());//设置客户端网络IO处理器
                            }
                        });
                //连接服务器 同步等待成功
                ChannelFuture f=b.connect(new InetSocketAddress(host,port));
                //同步等待客户端通道关闭
//                f.channel().closeFuture().sync();
            Channel channel = f.sync().channel();


            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("请输入");
                CarData carData = new CarData();
                String line = scanner.nextLine();
                carData.setBody(line);

                //发送请求
                channel.writeAndFlush(carData);
//                channel.write(carData);
            }


//                //释放线程组资源
//                group.shutdownGracefully();

        }

}
