package com.aolifu.mybatis;

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
    public void test4() throws IOException {
        Path path = Paths.get("/Users/wangqiang/Downloads/temp/20220327_hippo4j-grafana.json");

        FileChannel fc = FileChannel.open(path, StandardOpenOption.READ);

        long length = fc.size();

        ByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, length);

        int size = buffer.limit();

        byte[] data = new byte[size];

        buffer.get(data);

        String msg = new String(data, 0, data.length, StandardCharsets.UTF_8);

        System.out.println(msg);
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
    public void fileChannelTest() throws IOException {
        File file = new File("/Users/wangqiang/Downloads/temp/20220327_hippo4j-grafana.json");
        final FileChannel channel = new RandomAccessFile(file,"rw").getChannel();
        channel.position(100);

        System.out.println(channel.size());
    }

    @Test
    public void longTest(){
        Boolean a = null;
        if (a) {
            System.out.println("111");
        }
    }

    @Test
    public void crc32Test() throws Exception {
        String path1 = "/Users/wangqiang/Downloads/temp/20220327_hippo4j-grafana.json";
        String path2 = "/Users/wangqiang/Downloads/temp/20220327_hippo4j-grafana2.json";
        String fileCRCCode1 = getFileCRCCode(path1);
        String fileCRCCode2 = getFileCRCCode(path2);
        System.out.println(fileCRCCode1);
        System.out.println(fileCRCCode2);
        System.out.println(fileCRCCode1.equals(fileCRCCode2));
    }

    public String getFileCRCCode(String path) throws Exception {
        File file = new File(path);
        FileInputStream fileinputstream = new FileInputStream(file);
        CRC32 crc32 = new CRC32();
        for (CheckedInputStream checkedinputstream = new CheckedInputStream(fileinputstream, crc32);
             checkedinputstream.read() != -1;
        ){}
            return Long.toHexString(crc32.getValue());
    }

}
