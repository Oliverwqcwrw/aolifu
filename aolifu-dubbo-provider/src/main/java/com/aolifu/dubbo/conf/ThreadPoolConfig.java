package com.aolifu.dubbo.conf;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor dynamicThreadPoolExecutor() {
        String threadPoolId = "20880";
        ThreadPoolExecutor dynamicExecutor = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .dynamicPool()
                .build();
        return dynamicExecutor;
    }
    
    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor customThreadPoolExecutor() {
        String threadPoolId = "custom-provider";
        ThreadPoolExecutor dynamicExecutor = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .dynamicPool()
                .build();
        return dynamicExecutor;
    }
}
