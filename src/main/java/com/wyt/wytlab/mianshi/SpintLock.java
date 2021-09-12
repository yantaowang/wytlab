package com.wyt.wytlab.mianshi;

import java.util.concurrent.atomic.AtomicReference;

public class SpintLock{
    private AtomicReference<Integer> reference = new AtomicReference<>();

    public void lock() {
        System.out.println(Thread.currentThread().getId() + " lock");
        while (!reference.compareAndSet(null,1)) {
        }
    }

    public void unlock() {
        reference.compareAndSet(1,null);
        System.out.println(Thread.currentThread().getId() + " unlock");
    }
}
