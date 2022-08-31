package com.aolifu.rabbitmq;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import com.rabbitmq.jms.client.message.RMQTextMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

public class SendMessagWithJms {

    public static void main(String[] args) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        RMQDestination rmqDestination = new RMQDestination();
        rmqDestination.setDestinationName("testQueue");
        rmqDestination.setAmqpExchangeName("testExchange");
        rmqDestination.setAmqpRoutingKey("testRoutingKey");
        rmqDestination.setAmqpQueueName("testQueue");
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


        final QueueConnection connection = connectionFactory.createQueueConnection();
        final QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        final MessageProducer producer = session.createProducer(rmqDestination);
        Message message = new RMQTextMessage();
        message.setStringProperty("message", "22222");
        producer.send(message);
    }
}
