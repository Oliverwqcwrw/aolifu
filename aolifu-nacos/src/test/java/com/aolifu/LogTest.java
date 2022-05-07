package com.aolifu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogTest {
    
    @Test
    public void test(){
        try {
            int i = 1;
            final int i1 = i / 0;
        } catch (Exception e) {
            log.error("测试异常信息打印{}",e);
        }
    }

    @Test
    public void hashCodeTest(){
        System.out.println("aaa".hashCode());
    }
}
