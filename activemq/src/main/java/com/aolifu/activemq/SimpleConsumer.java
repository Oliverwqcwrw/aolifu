/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * The SimpleQueueReceiver class consists only of a main method,
 * which fetches one or more messages from a queue using
 * synchronous message delivery.  Run this program in conjunction
 * with SimpleQueueSender.  Specify a queue name on the command
 * line when you run the program.
 */

/**
 * The SimpleQueueReceiver class consists only of a main method,
 * which fetches one or more messages from a queue using
 * synchronous message delivery.  Run this program in conjunction
 * with SimpleQueueSender.  Specify a queue name on the command
 * line when you run the program.
 */
package com.aolifu.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * A simple polymorphic JMS consumer which can work with Queues or Topics which
 * uses JNDI to lookup the JMS connection factory and destination
 *
 *
 */
public final class SimpleConsumer implements ExceptionListener {

    public static void main(String[] args) {
        new SimpleConsumer().receiveMsg(args);
    }

    /**
     * @param args the queue used by the example
     */
    public  void receiveMsg(String[] args) {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("testQueue");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (
            Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }

    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

}
