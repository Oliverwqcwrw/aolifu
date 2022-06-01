package com.aolifu.rocketmq.config;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ResizableCapacityLinkedBlockIngQueue;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor dynamicThreadPoolExecutor() {
        String threadPoolId = "message-consume";
        ThreadPoolExecutor dynamicExecutor = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .corePoolSize(5)
                .maxPoolNum(10)
                .workQueue(new ResizableCapacityLinkedBlockIngQueue(1024))
                .rejected(new ThreadPoolExecutor.AbortPolicy())
                .keepAliveTime(6000, TimeUnit.MILLISECONDS)
                .dynamicPool()
                .build();
        return dynamicExecutor;
    }

}
