package com.aolifu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author ycw
 * @version v1.0
 * @date 2022/3/25 15:59
 * @description netty客户端
 */
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        //启动类
        final ChannelFuture localhost = new Bootstrap()
                //添加EventLoop
                .group(new NioEventLoopGroup())
                //选择客户端channel实现
                .channel(NioSocketChannel.class)
                //添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nc) throws Exception {
                        nc.pipeline().addLast(new StringEncoder());
                    }
                })
                //链接到服务器
                .connect(new InetSocketAddress("localhost", 10001)).sync().channel()
                //向服务器发送数据
                .writeAndFlush("Hello，World");
    
    }
}
