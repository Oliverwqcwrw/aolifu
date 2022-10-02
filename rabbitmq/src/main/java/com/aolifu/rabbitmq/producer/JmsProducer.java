package com.aolifu.rabbitmq.producer;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.client.message.RMQTextMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class JmsProducer {

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

        final MessageProducer producer = session.createProducer(destination);
        RMQTextMessage message = new RMQTextMessage();
        message.setText("Hello");
        producer.send(message);
        System.out.println("send  success");
    }
}
