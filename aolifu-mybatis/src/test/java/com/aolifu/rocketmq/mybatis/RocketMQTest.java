package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.MybatisApplication;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = MybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketMQTest {
    
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    
    private static final String TEST_TOPIC = "TEST_TOPIC";
    
    @Test
    @Ignore
    public void syncSendMsgTest() {
        SendResult sendResult = rocketMQTemplate.syncSend(TEST_TOPIC, "Hello, World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TEST_TOPIC, sendResult);
    }
    
}
