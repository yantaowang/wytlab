package com.wyt.wytlab.mianshi;

import java.util.concurrent.TimeUnit;

public class HoldThreadDemo implements Runnable{
    private String lockA = "";
    private String lockB = "";

    public HoldThreadDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println("get " + lockA);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("get " + lockB);
            }
        }
    }
}
