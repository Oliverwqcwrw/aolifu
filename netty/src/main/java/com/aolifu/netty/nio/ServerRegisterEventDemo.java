package com.aolifu.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ServerRegisterEventDemo {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        //注册selector事件
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动...");

        while (true) {

            //阻塞等待事件发生
            selector.select();

            //获取selector中注册的全部事件的SelectionKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {

                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    //注册读事件，需要向客户端发数据可注册写事件
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");

                } else if (selectionKey.isReadable()) {

                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int len = client.read(buffer);
                    if (len > 0) {
                        System.out.println("收到客户端消息：" + new String(buffer.array(), StandardCharsets.UTF_8));
                    } else if (len == -1) {
                        System.out.println("客户端断开连接");
                        client.close();
                    }
                }

                //从事件集合中移除本次已处理的key，防止下次重复处理
                iterator.remove();
            }

        }
    }


}