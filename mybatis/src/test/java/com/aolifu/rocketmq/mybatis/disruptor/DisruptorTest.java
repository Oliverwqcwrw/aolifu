package com.aolifu.rocketmq.mybatis.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) {
        // 2的n次方
        int bufferSize = 8;

        Disruptor<EventData> disruptor = new Disruptor<EventData>(
                () -> new EventData(), // 事件工厂
                bufferSize,            // 环形数组大小
                Executors.defaultThreadFactory(),       // 线程池工厂
                ProducerType.MULTI,    // 支持多事件发布者
                new BlockingWaitStrategy());    // 等待策略

        // 设置消费者
        disruptor.handleEventsWithWorkerPool(
                new EventConsumer(),
                new EventConsumer(),
                new EventConsumer(),
                new EventConsumer());

        disruptor.start();

        RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();
        EventProducer eventProducer = new EventProducer(ringBuffer);
        long i  = 0;
        for(;;){
            i++;
            eventProducer.sendData(i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}