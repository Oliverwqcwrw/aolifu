package com.aolifu.rabbitmq;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnection;

public class ReceiveMessageWithJms {

    public static void main(String[] args) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        RMQDestination  rmqDestination = new RMQDestination();
        rmqDestination.setDestinationName("testQueue3");
        rmqDestination.setAmqpExchangeName("testExchange3");
        rmqDestination.setAmqpRoutingKey("testRoutingKey3");
        rmqDestination.setAmqpQueueName("testQueue3");
        rmqDestination.setAmqp(true);


        final TopicConnection connection = connectionFactory.createTopicConnection();
        final Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        final MessageConsumer consumer = session.createConsumer(rmqDestination);
        consumer.setMessageListener(new MessageListener() {
            @Override public void onMessage(Message message) {
                try {
                    final String id = message.getJMSMessageID();
                    final String message1 = message.getStringProperty("message");
                    System.out.println(message1);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}
