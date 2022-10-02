package com.aolifu.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendMessage {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        String exchangeName = "testExchange3";
        String routingKey = "testQueue3";
        Connection conn = connectionFactory.newConnection();
        final Channel channel = conn.createChannel();
        channel.exchangeDeclare(exchangeName, "topic", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, routingKey);

        byte[] messageBodyBytes = "Hello, world!".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
        channel.close();


    }
}
