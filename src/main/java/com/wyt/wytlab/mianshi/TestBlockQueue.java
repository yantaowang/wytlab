package com.wyt.wytlab.mianshi;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockQueue {
    public static void main(String[] args) throws InterruptedException {
//        showTeshu();
//        showPut();
    }

    public static void showPut() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    public static void showTeshu() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.offer("a",3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("v"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    public static void showException() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        blockingQueue.add("a");

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
