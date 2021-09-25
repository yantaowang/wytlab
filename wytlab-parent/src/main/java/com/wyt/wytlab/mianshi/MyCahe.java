package com.wyt.wytlab.mianshi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCahe {
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object value) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在写入" + key);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入完成" + key);
        readWriteLock.writeLock().unlock();
    }
    public void get(String key) {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在读取");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object object = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成" + object);
        readWriteLock.readLock().unlock();
    }


}
