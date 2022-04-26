package com.aolifu.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.ResponseCode;
import org.apache.rocketmq.remoting.exception.RemotingException;

import javax.annotation.PostConstruct;

public class RocketMQProducer {

    private DefaultMQProducer producer = new DefaultMQProducer();

    @PostConstruct
    public void start() throws MQClientException {
        // 失败默认重试次数
        producer.setRetryTimesWhenSendFailed(10);
        // 发送消息默认超时时间
        producer.setSendMsgTimeout(5000);
        producer.start();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public void sendMessage(Message message) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        SendResult sendResult = producer.send(message);
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            throw new MQClientException(ResponseCode.SYSTEM_ERROR,"消息发送失败");
        }
    }
}
