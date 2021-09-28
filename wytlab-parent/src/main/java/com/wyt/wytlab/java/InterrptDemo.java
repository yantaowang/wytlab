package com.wyt.wytlab.java;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

public class InterrptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                LockSupport.park();
                System.out.println("11111");
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("hh");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(22222);
    }
}
