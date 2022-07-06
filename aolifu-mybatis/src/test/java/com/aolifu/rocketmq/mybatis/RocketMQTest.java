package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.MybatisApplication;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@SpringBootTest(classes = MybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketMQTest {
    
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    
    private static final String TEST_TOPIC = "TEST_TOPIC";
    
    @Test
    @Ignore
    public void syncSendMsgTest()
            throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT_GROUP", true);
        producer.setNamesrvAddr("192.168.137.226:9876");
        Message message = new MessageExt();
        message.setTopic("TEST_TOPIC");
        message.setBody("Hello World".getBytes("UTF-8"));
        producer.start();
        final SendResult send = producer.send(message);
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TEST_TOPIC, send);
    }
    
}
