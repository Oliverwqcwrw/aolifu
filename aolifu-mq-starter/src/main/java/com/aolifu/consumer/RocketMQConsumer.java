package com.aolifu.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class RocketMQConsumer  implements ApplicationRunner {

    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();


    @Override
    public void run(ApplicationArguments args) throws MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setMaxReconsumeTimes(10);
        consumer.start();
    }

    public DefaultMQPushConsumer getConsumer(){
        return consumer;
    }
}
