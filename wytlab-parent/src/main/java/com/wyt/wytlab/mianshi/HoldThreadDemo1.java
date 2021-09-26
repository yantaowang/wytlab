package com.wyt.wytlab.mianshi;

public class HoldThreadDemo1 {
    private String lockA= "A";
    private String lockB = "B";

    public void testA () throws InterruptedException {
        synchronized (lockA) {
            System.out.println("get " + lockA);
            Thread.sleep(2000);
            synchronized (lockB) {
                System.out.println("get " + lockB);
            }
        }
    }

    public void testB () {
        synchronized (lockB) {
            System.out.println("get " + lockB);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockA) {
                System.out.println("get " + lockA);
            }
        }
    }


}
