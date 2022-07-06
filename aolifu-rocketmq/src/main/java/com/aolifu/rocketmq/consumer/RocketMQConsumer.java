package com.aolifu.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class RocketMQConsumer  implements ApplicationRunner {
    
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DEFAULT_CONSUME_GROUP", true);
    
    
    @Override
    public void run(ApplicationArguments args) throws MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setMaxReconsumeTimes(10);
        // 最大消费消息数量
        consumer.setConsumeMessageBatchMaxSize(10);
        // 消费组
        consumer.setConsumerGroup("DEFAULT_GROUP");
        // 设置最大消费线程数
        consumer.setConsumeThreadMax(20);
        // 设置拉取消息的数量
        consumer.setPullBatchSize(32);
        // 设置拉取请求间隔
        consumer.setPullInterval(0);
        consumer.start();
    }
    
    public DefaultMQPushConsumer getConsumer(){
        return consumer;
    }
}
