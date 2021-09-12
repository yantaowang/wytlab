package com.wyt.wytlab.mianshi;

import java.util.concurrent.ArrayBlockingQueue;

public class TestBlockingProduceConsumer {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer(new ArrayBlockingQueue<Integer>(3));
        new Thread(() -> {
            producerConsumer.product();
        }, "AA").start();
        new Thread(() -> {
            producerConsumer.consumer();
        }, "BB").start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producerConsumer.stop();
    }
}