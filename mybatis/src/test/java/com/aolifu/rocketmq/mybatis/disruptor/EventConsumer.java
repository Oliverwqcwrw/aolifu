package com.aolifu.rocketmq.mybatis.disruptor;

import com.lmax.disruptor.WorkHandler;

public class EventConsumer implements WorkHandler<EventData> {

    /**
     * 消费回调
     * @param eventData
     * @throws Exception
     */
    @Override
    public void onEvent(EventData eventData) throws Exception {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread() + ", eventData:" + eventData.getValue());
    }
}