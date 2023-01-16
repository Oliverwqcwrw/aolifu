package com.aolifu.netty;

import com.aolifu.netty.handler.EchoServerHandler;
import com.aolifu.netty.handler.ServerHandler;
import com.aolifu.netty.handler.TimeEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HelloServer {
    
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
        
        try {
            //1启动器 负责组装netty组件 启动服务器
            final ChannelFuture future = new ServerBootstrap()
                //2 BossEventLoopGroup,WorkerEventLoopGroup(selector,thread),group组
                .group(bossGroup, workGroup)
                //3 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //4boss负责链接worker（child）负责读写 ，决定了worker(child)能执行哪些操作（handler）
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //5channel 代表和客户端进行数据读写的通道 Initializer初始化负责添加别的handler
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //6 添加具体的 handler
                        final ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new TimeEncoder(), new EchoServerHandler());
                        pipeline.addLast(new IdleStateHandler(10, 0, 0));
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
                        pipeline.addLast(new ServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .bind(10008);
            final Channel channel = future.sync().channel();
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    
    }
}
