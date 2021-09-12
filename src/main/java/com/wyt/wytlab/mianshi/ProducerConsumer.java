package com.wyt.wytlab.mianshi;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<Integer> blockingQueue = null;

    public ProducerConsumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product() {
        Integer data = null;
        boolean result;
        while (flag) {
            data = atomicInteger.getAndIncrement();
            try {
                result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (result) {
                    System.out.println(Thread.currentThread().getName() + "生成成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "生成失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() {
        Integer result;
        while (flag) {
            try {
                result = blockingQueue.poll( 2, TimeUnit.SECONDS);
                if (result != null) {
                    System.out.println(Thread.currentThread().getName() + "消费成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "消费失败");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        this.flag = false;
    }
}
