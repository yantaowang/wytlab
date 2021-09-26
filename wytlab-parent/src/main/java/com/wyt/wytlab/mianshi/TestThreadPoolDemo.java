package com.wyt.wytlab.mianshi;

import java.util.concurrent.*;

public class TestThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS
                ,new LinkedBlockingQueue<>(3)
                ,Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}
