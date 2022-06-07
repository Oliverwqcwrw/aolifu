package com.aolifu.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class MyJob2 implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                System.out.println("MyJob2 sharding item 0");
                break;
            case 1:
                // do something by sharding item 1
                System.out.println("MyJob2 sharding item 1");
                break;
            case 2:
                // do something by sharding item 2
                System.out.println("MyJob2 sharding item 2");
                break;
            // case n: ...
        }
    }
    
}
