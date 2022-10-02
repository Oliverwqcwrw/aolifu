package com.aolifu.rabbitmq.consumer;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

public class JmsConsumer {

    public static void main(String[] args) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        final Connection connection = connectionFactory.createConnection("guest", "guest");
        connection.start();


        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final Destination destination = session.createTopic("testTopic1");

        final MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override public void onMessage(Message message) {
                try {
                    System.out.println(message.getJMSMessageID());
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
