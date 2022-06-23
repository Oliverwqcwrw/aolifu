package com.aolifu.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class MyJob implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        // job均匀分布在各个分片上执行，如果都是在分片0执行会导致单个服务器压力较大
        if (context.getShardingItem() == 0) {
            System.out.println("Deal Business");
        }
    }
}
