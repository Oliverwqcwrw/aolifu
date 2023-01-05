package com.aolifu.rocketmq.mybatis;

import org.junit.Test;

public class CommonTest {

    @Test
    public void test1() {
        String s = "ab";
        if (s.contains("a")) {
            System.out.println("a");
        } else if(s.contains("b")) {
            System.out.println("b");
        }
    }
}
