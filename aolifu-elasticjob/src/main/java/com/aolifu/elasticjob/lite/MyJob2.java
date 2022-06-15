package com.aolifu.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class MyJob2 implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        if (context.getShardingItem() == 0) {
            System.out.println("Deal Business");
        }
    }
    
}
