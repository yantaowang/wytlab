package com.wyt.wytlab.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 */
@Slf4j
public class SyncDemo2 {

    private static int counter = 0;

    private static String lock = "";

    public static void increment() {
        synchronized (lock){
                counter++;
        }
    }

    public static void decrement() {
        synchronized (lock) {
            counter--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
                for (int i = 0; i < 5000; i++) {
                    increment();
                }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //思考： counter=？
        log.info("counter={}", counter);
    }
}
