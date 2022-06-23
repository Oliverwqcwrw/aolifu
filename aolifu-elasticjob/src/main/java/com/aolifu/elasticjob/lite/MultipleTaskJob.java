package com.aolifu.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * 多任务执行，分散到不同的分片并行执行
 */
@Component
public class MultipleTaskJob implements SimpleJob {
    
    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
            case 0:
                System.out.println("Task1");
                break;
            case 1:
                System.out.println("Task2");
                break;
            case 2:
                System.out.println("Task3");
                break;
        }
    }
}
