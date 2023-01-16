package com.aolifu.netty.handler;

import com.aolifu.netty.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
       ctx.writeAndFlush(msg);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        // final ByteBuf time = ctx.alloc().buffer(4); // (2)

        final ChannelFuture future = ctx.writeAndFlush(new UnixTime()); // (3)
        future.addListener((ChannelFutureListener) item -> {
            assert future == item;
            // IO operation end
            final ChannelFuture closeFuture = ctx.close();
            closeFuture.addListener(ChannelFutureListener.CLOSE);
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
