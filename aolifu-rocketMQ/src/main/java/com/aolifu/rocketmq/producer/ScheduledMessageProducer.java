package com.aolifu.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author wangqiang32
 * @date 2022/3/28
 */
public class ScheduledMessageProducer {
    
     public static void main(String[] args) throws Exception {
         // Instantiate a producer to send scheduled messages
         DefaultMQProducer producer = new DefaultMQProducer("SCHEDULED_MESSAGE_GROUP");
         producer.setNamesrvAddr("localhost:9876");
         // Launch producer
         producer.start();
         int totalMessagesToSend = 30;
         for (int i = 0; i < totalMessagesToSend; i++) {
             Message message = new Message("SCHEDULED_MESSAGE_TOPIC", ("Hello scheduled message " + i).getBytes());
             // This message will be delivered to consumer 10 seconds later.
             message.setDelayTimeLevel(3);
             // Send the message
             producer.send(message);
             System.out.println(message);
         }
    
         // Shutdown producer after use.
         producer.shutdown();
     }
        
 }