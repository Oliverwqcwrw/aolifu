package com.aolifu;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableDynamicThreadPool
@SpringBootApplication
public class DynamicTpApplication {
    public static void main( String[] args ) {
        SpringApplication.run(DynamicTpApplication.class, args);
    }
}
