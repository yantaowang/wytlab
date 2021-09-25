package com.wyt.wytlab.mianshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerDemo {
    private Integer number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        while (number != 0) {
            condition.await();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "----"+ number);
        condition.signalAll();
        lock.unlock();
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        while (number ==0){
            condition.await();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "-----" + number);
        condition.signalAll();
        lock.unlock();
    }
}
