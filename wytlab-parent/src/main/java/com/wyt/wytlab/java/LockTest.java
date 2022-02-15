package com.wyt.wytlab.java;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        ReentrantLock reetrantLock = new ReentrantLock(true);
        reetrantLock.lock();
        reetrantLock.unlock();
    }
}
