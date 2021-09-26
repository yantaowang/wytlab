package com.wyt.wytlab.mianshi;

public class TestHoldThread {
    public static void main(String[] args) {
//        String lockA= "lockA";
//        String lockB = "lockB";
//
//        new Thread(new HoldThreadDemo(lockA,lockB),"AA").start();
//        new Thread(new HoldThreadDemo(lockB,lockA),"BB").start();

        HoldThreadDemo1 holdThreadDemo1 = new HoldThreadDemo1();
        new Thread(()->{
            try {
                holdThreadDemo1.testA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aa").start();

        new Thread(()->{
            holdThreadDemo1.testB();
        },"bb").start();
    }
}
