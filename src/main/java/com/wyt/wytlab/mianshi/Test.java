package com.wyt.wytlab.mianshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdde = new LongAdder();
        longAdde.add(1);
        System.out.println(longAdde.intValue());
    }
    public synchronized void f(){    //这个是同步方法
        System.out.println("Hello world");
    }
}
