package com.aolifu.rocketmq.mybatis;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class ExpandTest {

    @Test
    public void expandTest(){
        HashMap<String, String> map = new HashMap<>(10000);
        final long start1 = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value");
        }
        System.out.println("Expansion takes nanoTime = " + (System.nanoTime() - start1));

        HashMap<String,String> map2 = new HashMap<>(10000, 1);
        final long start2 = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            map2.put("key" + i, "value");
        }
        System.out.println("Without expansion takes nanoTime = " + (System.nanoTime() - start2));
    }

    @Test
    public void expandTest2(){
        HashMap<String,String> map2 = new HashMap<>(10000, 1);
        final long start2 = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            map2.put("key" + i, "value");
        }
        System.out.println("Without expansion takes nanoTime = " + (System.nanoTime() - start2));

        HashMap<String, String> map = new HashMap<>(10000);
        final long start1 = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value");
        }
        System.out.println("Expansion takes nanoTime = " + (System.nanoTime() - start1));
    }

    @Test
    public void test2(){
        int[] arr1 = new int[]{1,2,3,4,5,6,7,1,1,1,1,1,1,1,2};

        int[] arr2 = new int[arr1.length];
        final long start1 = System.nanoTime();
        for (int i =0;i < arr1.length;i++){
            arr2[i] = arr1[i];
        }
        System.out.println("First spend time :" +  (System.nanoTime() -start1));

        final long start2 = System.nanoTime();
        int[] arr3 = Arrays.copyOf(arr1,arr1.length);
        System.out.println("Second spend time:" + (System.nanoTime() - start2));
    }

    enum SexEnum{
        MALE,
        FEMALE
    }

    @Test
    public void byteBufferTest(){
        byte[] arr = new byte[]{1,2,3,4,5};
        ByteBuffer buffer1  = ByteBuffer.wrap(arr);
        System.out.println(buffer1);

        final ByteBuffer slice = buffer1.slice();

        slice.put((byte) 6);
        System.out.println(slice);
    }
    
    @Test
    public void regexTest() {
        String confStr = "cloud-legionzone-*,game-ms-*,cloud-pc-browser";
        final String[] split = confStr.split(",");
        String target = "cloud-legionzone-zone";
        System.out.println(ReUtil.isMatch(split[0], target));
        
    }
    
    @Test
    public void stringCompareTest() {
        String topica = "TEST1";
        String topicb = "TEST2";
        System.out.println(topica.compareTo(topicb));
    }

}
