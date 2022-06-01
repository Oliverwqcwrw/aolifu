package com.aolifu.rocketmq.example;

import com.aolifu.rocketmq.consumer.RocketMQConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConsumer {

    /**
     * 默认消费者
     * @return
     */
    @Bean
    public RocketMQConsumer rocketMQConsumer(){
        RocketMQConsumer consumer = new RocketMQConsumer();
        final DefaultMQPushConsumer pushConsumer = consumer.getConsumer();
        pushConsumer.setNamesrvAddr("127.0.0.1:9876");
        pushConsumer.setMaxReconsumeTimes(Integer.MAX_VALUE);
        pushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            System.out.println();
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        return consumer;
    }

    /**
     * legion消费者
     * @return
     */
    @Bean
    public RocketMQConsumer legionRocketMQConsumer(){
        RocketMQConsumer consumer = new RocketMQConsumer();
        final DefaultMQPushConsumer pushConsumer = consumer.getConsumer();
        pushConsumer.setNamesrvAddr("127.0.0.1:9877");
        pushConsumer.setMaxReconsumeTimes(100);
        pushConsumer.setConsumeThreadMax(20);
        pushConsumer.setConsumeThreadMin(5);
        pushConsumer.setVipChannelEnabled(false);
        pushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            System.out.println("legion 消费者消费" + list.get(0));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        return consumer;
    }
}
