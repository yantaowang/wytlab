package com.wyt.wytlab.mianshi;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread {
    volatile int number = 0;
    AtomicInteger n = new AtomicInteger();
    public void add() {
        number = 60;
    }
    public void addPlus() {
        number++;
    }
    public void addAtomic() {
        n.getAndIncrement();
    }
}
