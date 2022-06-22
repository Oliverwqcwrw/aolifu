package com.aolifu.rocketmq.example;

import com.aolifu.rocketmq.producer.RocketMQProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleProducer extends RocketMQProducer{

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        RocketMQProducer producer = new RocketMQProducer();
        final DefaultMQProducer defaultMQProducer = producer.getProducer();
        // 自定义设置参数 和配置中心解耦 支持每一个生产者连接个性化的nameserver
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        producer.sendMessage(new Message());
    }

    /**
     * 默认生产者
     * @return
     */
    @Bean
    public RocketMQProducer rocketMQProducer(){
        RocketMQProducer producer = new RocketMQProducer();
        final DefaultMQProducer defaultMQProducer = producer.getProducer();
        // 自定义设置参数 和配置中心解耦 支持每一个生产者连接个性化的nameserver
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        return producer;
    }

    /**
     * legion生产者
     * @return
     */
    @Bean
    public RocketMQProducer legionRocketMQProducer(){
        RocketMQProducer producer = new RocketMQProducer();
        final DefaultMQProducer defaultMQProducer = producer.getProducer();
        // 自定义设置参数 和配置中心解耦 支持每一个生产者连接个性化的nameserver
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9877");
        return producer;
    }

}
