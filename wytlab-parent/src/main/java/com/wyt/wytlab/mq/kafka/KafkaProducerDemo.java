package com.wyt.wytlab.mq.kafka;

import com.wyt.wytlab.mq.kafka.kimmking.ProducerImpl;

public class KafkaProducerDemo {

    public static void main(String[] args) {
        testProducer();
    }

    private static void testProducer() {
        ProducerImpl producer = new ProducerImpl();
//        for (int i = 0; i < 1000; i++) {
//            producer.send(new Order(1000L + i,System.currentTimeMillis(),"USD2CNY", 6.5d));
//            producer.send(new Order(2000L + i,System.currentTimeMillis(),"USD2CNY", 6.51d));
//        }

        producer.send(new Order(1000L,System.currentTimeMillis(),"USD2CNY", 6.5d));
    }
}
