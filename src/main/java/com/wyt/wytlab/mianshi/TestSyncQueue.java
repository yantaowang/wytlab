package com.wyt.wytlab.mianshi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSyncQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                blockingQueue.put("a");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
                blockingQueue.put("b");
                System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
                blockingQueue.put("c");
                System.out.println("cccccccccccccccccccccc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB").start();
    }
}
