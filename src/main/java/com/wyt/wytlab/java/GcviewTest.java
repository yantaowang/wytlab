package com.wyt.wytlab.java;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GcviewTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];

        byte[] array6 = new byte[2 * 1024 * 1024];
        new Thread(()->{
            System.out.println(111);
        }, "thread1").start();

        while (true) {
            Thread.sleep(1000);
        }
    }
}
