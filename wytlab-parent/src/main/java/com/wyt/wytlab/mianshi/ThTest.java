package com.wyt.wytlab.mianshi;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public class ThTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(15);
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                atomicInteger.getAndSet(atomicInteger.get() - 5);
                System.out.println(atomicInteger.get());
            },String.valueOf(i)).start();
        }

        while (true) {
            System.out.println(atomicInteger.get());
            Thread.sleep(3000);
        }
    }
}
