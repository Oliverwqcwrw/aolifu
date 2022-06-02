package com.aolifu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author ycw
 * @version v1.0
 * @date 2022/3/25 15:02
 * @description netty作为服务端
 */
public class HelloServer {
    
    public static void main(String[] args) {
        //1启动器 负责组装netty组件 启动服务器
        new ServerBootstrap()
                //2 BossEventLoopGroup,WorkerEventLoopGroup(selector,thread),group组
                .group(new NioEventLoopGroup())
                //3 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //4boss负责链接worker（child）负责读写 ，决定了worker(child)能执行哪些操作（handler）
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //5channel 代表和客户端进行数据读写的通道 Initializer初始化负责添加别的handler
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //6 添加具体的 handler
                        ch.pipeline().addLast(new StringDecoder());//将ByteBuf转化为string
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("读到数据" + msg);
                            }
                        });
                    }
                }).bind(10001);
        
    }
}
