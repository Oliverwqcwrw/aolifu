package com.aolifu.rocketmq.mybatis.rocketmq;


import io.openmessaging.BytesMessage;
import io.openmessaging.Future;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.consumer.PushConsumer;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;
import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class OpenMessagingTest {
    
    private static final String OPEN_MESSAGING_TOPIC = "OPEN_MESSAGING_TOPIC";
    
    private static final String URL= "oms:rocketmq://192.168.137.46:9876/default:default";
    
    private static final String BODY = "Hello OpenMessaging";
    
    private static final String QUEUE = "OMS_CONSUMER";
    
    @Test
    public void omsProducerTest() {
        // You need to set the environment variable OMS_RMQ_DIRECT_NAME_SRV=true
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint(URL);
    
        final Producer producer = messagingAccessPoint.createProducer();
    
        messagingAccessPoint.startup();
        System.out.printf("MessagingAccessPoint startup OK%n");
    
        producer.startup();
        System.out.printf("Producer startup OK%n");
        final BytesMessage bytesMessage = producer.createBytesMessage(OPEN_MESSAGING_TOPIC,
                BODY.getBytes(StandardCharsets.UTF_8));
    
        // sync send
        SendResult sendResult = producer.send(bytesMessage);
        System.out.printf("Send sync message OK, msgId: %s%n", sendResult.messageId());
        
        // async send
        final Future<SendResult> sendResultFuture = producer.sendAsync(bytesMessage);
        final SendResult asyncResult = sendResultFuture.get();
        System.out.println(asyncResult);
        
        
        // oneway send
        producer.sendOneway(bytesMessage);
        
        producer.shutdown();
        messagingAccessPoint.shutdown();
    }
    
    @Test
    public void omsPullConsumerTest() {
        // You need to set the environment variable OMS_RMQ_DIRECT_NAME_SRV=true
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint(URL);
    
        messagingAccessPoint.startup();
    
        final Producer producer = messagingAccessPoint.createProducer();
    
        final PullConsumer consumer = messagingAccessPoint.createPullConsumer(
                OMS.newKeyValue().put(OMSBuiltinKeys.CONSUMER_ID, QUEUE));
    
        messagingAccessPoint.startup();
        System.out.printf("MessagingAccessPoint startup OK%n");
        
        producer.startup();
        Message msg = producer.createBytesMessage(OPEN_MESSAGING_TOPIC, "Hello Open Messaging".getBytes());
        SendResult sendResult = producer.send(msg);
        System.out.printf("Send Message OK. MsgId: %s%n", sendResult.messageId());
        producer.shutdown();
    
        consumer.attachQueue(OPEN_MESSAGING_TOPIC);
    
        consumer.startup();
        System.out.printf("Consumer startup OK%n");
    
        // Keep running until we find the one that has just been sent
        boolean stop = false;
        while (!stop) {
            Message message = consumer.receive();
            if (message != null) {
                String msgId = message.sysHeaders().getString(Message.BuiltinKeys.MESSAGE_ID);
                System.out.printf("Received one message: %s%n", msgId);
                consumer.ack(msgId);
            
                if (!stop) {
                    stop = msgId.equalsIgnoreCase(sendResult.messageId());
                }
            
            } else {
                System.out.printf("Return without any message%n");
            }
        }
    
        consumer.shutdown();
        messagingAccessPoint.shutdown();
    }
    
    @Test
    @SneakyThrows
    public void omsPushConsumerTest() {
        // You need to set the environment variable OMS_RMQ_DIRECT_NAME_SRV=true
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint(URL);
        final Producer producer = messagingAccessPoint.createProducer();
        final BytesMessage bytesMessage = producer.createBytesMessage(OPEN_MESSAGING_TOPIC,
                BODY.getBytes(StandardCharsets.UTF_8));
        producer.startup();
        final SendResult sendResult = producer.send(bytesMessage);
        System.out.println("sendResult: " + sendResult);
        producer.shutdown();
        final PushConsumer consumer = messagingAccessPoint.
                createPushConsumer(OMS.newKeyValue().put(OMSBuiltinKeys.CONSUMER_ID, QUEUE));
    
        messagingAccessPoint.startup();
        System.out.printf("MessagingAccessPoint startup OK%n");
    
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            consumer.shutdown();
            messagingAccessPoint.shutdown();
        }));
    
        consumer.attachQueue(OPEN_MESSAGING_TOPIC, (message, context) -> {
            System.out.printf("Received one message: %s%n", message.sysHeaders().getString(Message.BuiltinKeys.MESSAGE_ID));
            context.ack();
        });
    
        consumer.startup();
        System.out.printf("Consumer startup OK%n");
        TimeUnit.SECONDS.sleep(30);
    }

}
