package com.aolifu.rocketmq.mybatis.disruptor;

import com.lmax.disruptor.RingBuffer;

public class EventProducer {

    private final RingBuffer<EventData> ringBuffer;

    public EventProducer(RingBuffer<EventData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(Long v){
        // cas展位
        long next = ringBuffer.next();
        try {
            EventData eventData = ringBuffer.get(next);
            eventData.setValue(v);
        } finally {
            // 通知等待的消费者
            System.out.println("EventProducer send success, sequence:"+next);
            ringBuffer.publish(next);
        }
    }
}
