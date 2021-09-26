package com.wyt.wytlab.dubbo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        System.out.println(blockingQueue.size());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        int offset = ThreadLocalRandom.current().nextInt(3);
        System.out.println(offset);

        String path = "wang/123";
        System.out.println(path.substring(path.indexOf("/")));

        System.out.println(Person.class.getName());
    }
}
