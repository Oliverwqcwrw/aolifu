package com.aolifu.mybatis;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapBlokcTest {

    static final int MAP_SIZE=20;
    static final int THREADS=20;
    static final Map<Integer,Integer> map = new ConcurrentHashMap<>();
    static {
        for (int i = 0; i < MAP_SIZE; i++) map.put(i, i);
    }
    static class TestThread extends Thread {
        public void run() {
            int i=0; int result =0;
            while(result<Integer.MAX_VALUE) {
                i = (i+1) % MAP_SIZE;
                result += map.computeIfAbsent(i, (key)->key+key);
            }
        }
    }
    public static void main(String[]v) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i=0;i<THREADS;i++) {
            TestThread t = new TestThread();
            threads.add(t);
            t.start();
        }
        threads.get(0).join();
    }
}
