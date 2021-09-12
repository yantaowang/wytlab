package com.wyt.wytlab.mianshi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(()->{
                try {
                    System.out.println("start:" + Thread.currentThread().getId() );
                    cyclicBarrier.await();
                    System.out.println("end:" + Thread.currentThread().getId() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
