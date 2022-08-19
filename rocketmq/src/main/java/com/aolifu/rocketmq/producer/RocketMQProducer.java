package com.aolifu.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.ResponseCode;
import org.apache.rocketmq.remoting.exception.RemotingException;

import javax.annotation.PostConstruct;
import java.util.List;

public class RocketMQProducer {
    
    private DefaultMQProducer producer = new DefaultMQProducer();
    
    @PostConstruct
    public void start() throws MQClientException {
        // 失败默认重试次数
        producer.setRetryTimesWhenSendFailed(10);
        // 发送消息默认超时时间
        producer.setSendMsgTimeout(5000);
        producer.setProducerGroup("DEFAULT_PRODUCER_GROUP");
        // 消息体超过4K启用压缩
        producer.setCompressMsgBodyOverHowmuch(1024 * 4);
        // 发送失败换一个broker重试
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        // 消息体允许的最大值 4M
        producer.setMaxMessageSize(1024 * 1024 * 4);
        // 队列数
        producer.setDefaultTopicQueueNums(12);
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
    
    public void sendBatchMessage(List<Message> messageList) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        SendResult sendResult = producer.send(messageList);
        
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            throw new MQClientException(ResponseCode.SYSTEM_ERROR,"批量消息发送失败");
        }
    }
}
