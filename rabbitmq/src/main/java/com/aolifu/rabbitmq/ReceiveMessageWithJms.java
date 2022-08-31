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

public class ReceiveMessageWithJms {

    public static void main(String[] args) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        RMQDestination  rmqDestination = new RMQDestination();
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
