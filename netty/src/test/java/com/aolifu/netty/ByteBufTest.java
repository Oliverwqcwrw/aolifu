package com.aolifu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void test1() {
        final ByteBuf buffer = Unpooled.buffer();
        System.out.println(buffer.capacity());

        buffer.writeInt(1000);

        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.capacity());

        byte[] arr1 = new byte[4];
        buffer.writeBytes(arr1);

        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.capacity());

        //buffer.setInt(0, 1);
        buffer.setInt(4, 2);

        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.capacity());

        System.out.println(buffer.readableBytes());

    }
}
