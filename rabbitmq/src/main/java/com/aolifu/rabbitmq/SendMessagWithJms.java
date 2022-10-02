package com.aolifu.rabbitmq;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import com.rabbitmq.jms.client.message.RMQTextMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TopicConnection;

public class SendMessagWithJms {

    public static void main(String[] args) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        RMQDestination rmqDestination = new RMQDestination();
        rmqDestination.setDestinationName("testQueue3");
        rmqDestination.setAmqpExchangeName("testExchange3");
        rmqDestination.setAmqpRoutingKey("testRoutingKey3");
        rmqDestination.setAmqpQueueName("testQueue3");
        rmqDestination.setAmqp(true);

        connectionFactory.setConfirmListener(context -> {
            final Message message = context.getMessage();// the message that is confirmed/nack-ed
            try {
                System.out.println(message.getJMSMessageID());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
            context.isAck(); // whether the message is confirmed or nack-ed
        });


        final TopicConnection connection = connectionFactory.createTopicConnection();
        final Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        final MessageProducer producer = session.createProducer(rmqDestination);
        Message message = new RMQTextMessage();
        message.setStringProperty("message", "22222");
        producer.send(message);
        producer.close();
    }
}
