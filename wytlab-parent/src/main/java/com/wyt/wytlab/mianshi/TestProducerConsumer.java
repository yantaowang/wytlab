package com.wyt.wytlab.mianshi;

public class TestProducerConsumer {
    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    producerDemo.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"AA").start();
        }

//        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                try {
//                    producerDemo.decrement();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"BB").start();
//        }

    }
}
