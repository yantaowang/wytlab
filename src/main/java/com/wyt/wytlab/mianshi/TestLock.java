package com.wyt.wytlab.mianshi;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    private static int SIZE = 1000000;//预计要插入多少数据

    private static double FPP = 0.001;//期望的误判率

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE, FPP);

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        },"t1").start();
        new Thread(()->{
            phone.sendSms();
        },"t2").start();
        Thread.sleep(1000);

        new Thread(phone).start();
        new Thread(phone).start();

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock(5, TimeUnit.SECONDS);
        try {
            for (int i = 0; i < 1000000; i++) {
                bloomFilter.put(i);
            }
            int count = 0;
            for (int i = 1000000; i < 2000000; i++) {
                if (bloomFilter.mightContain(i)) {
                    count++;
//                System.out.println(i + "误判了");
                }
            }
            System.out.println("总共的误判数:" + count);
        } finally {
            reentrantLock.unlock();
        }
    }
}
