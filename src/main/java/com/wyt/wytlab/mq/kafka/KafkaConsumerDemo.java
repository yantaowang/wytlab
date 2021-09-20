package com.wyt.wytlab.mq.kafka;


import com.wyt.wytlab.mq.kafka.kimmking.ConsumerImpl;

public class KafkaConsumerDemo {

    public static void main(String[] args) {
        testConsumer();
    }

    private static void testConsumer() {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.consumeOrder();

    }
}
