package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.MybatisApplication;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest(classes = MybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketMQTest {
    
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    
    private static final String TEST_TOPIC = "TEST_TOPIC";
    
    private static final String ORDER_TOPIC = "ORDER_TOPIC";
    
    private static final String NAMESERVER_ADDR = "192.168.31.38:9876";
    
    @Test
    @Ignore
    @SneakyThrows
    public void syncSendMsgTest() {
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT_GROUP", true);
        producer.setNamesrvAddr("192.168.137.226:9876");
        Message message = new MessageExt();
        message.setTopic("TEST_TOPIC");
        message.setBody("Hello World".getBytes("UTF-8"));
        producer.start();
        final SendResult send = producer.send(message);
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TEST_TOPIC, send);
        producer.shutdown();
    }
    
    @Test
    @Ignore
    @SneakyThrows
    public void asyncSendMsgTest() {
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT_GROUP", true);
        producer.setNamesrvAddr("192.168.137.163:9876");
        Message message = new MessageExt();
        message.setTopic("TEST_TOPIC");
        message.setBody("Hello World".getBytes("UTF-8"));
        producer.start();
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("Async send msg success msgId: " + sendResult.getMsgId());
            }
    
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
        TimeUnit.SECONDS.sleep(3);
        
        producer.shutdown();
    }
    
    @Test
    @Ignore
    @SneakyThrows
    public void onewayTest() {
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT_GROUP", true);
        producer.setNamesrvAddr("192.168.137.163:9876");
        Message message = new MessageExt();
        message.setTopic("TEST_TOPIC");
        message.setBody("Hello World".getBytes("UTF-8"));
        producer.start();
        producer.sendOneway(message);
        producer.shutdown();
    }
    
    @Test
    @Ignore
    @SneakyThrows
    public void consumeTest() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
    
        // Specify name server addresses.
        consumer.setNamesrvAddr("192.168.137.163:9876");
    
        // Subscribe one more more topics to consume.
        consumer.subscribe("TEST_TOPIC", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    
        //Launch the consumer instance.
        consumer.start();
        TimeUnit.SECONDS.sleep(60);
        System.out.printf("Consumer Started.%n");
    }
    
    @Test
    @Ignore
    @SneakyThrows
    public void orderProducerTest() {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT_GROUP");
        producer.setNamesrvAddr(NAMESERVER_ADDR);
        //Launch the instance.
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 50; i++) {
            int orderId = i % 8;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(ORDER_TOPIC, tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
        
            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
    
    @Test
    @Ignore
    @SneakyThrows
    public void orderConsumerTest() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DEFAULT_GROUP");
        consumer.setNamesrvAddr(NAMESERVER_ADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    
        consumer.subscribe(ORDER_TOPIC, "TagA || TagC || TagD");
    
        consumer.registerMessageListener(new MessageListenerOrderly() {
        
            AtomicLong consumeTimes = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                    ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                this.consumeTimes.incrementAndGet();
                if ((this.consumeTimes.get() % 2) == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            
            }
        });
    
        consumer.start();
        TimeUnit.SECONDS.sleep(60);
        System.out.printf("Consumer Started.%n");
    }
    
}
