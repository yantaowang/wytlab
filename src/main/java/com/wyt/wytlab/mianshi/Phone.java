package com.wyt.wytlab.mianshi;

import java.util.concurrent.locks.ReentrantLock;

public class Phone implements Runnable {
    public synchronized void sendSms() {
        System.out.println(Thread.currentThread().getId() + "sendsms");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId()+ "sendemail");
    }

    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        set();
    }
    public void set() {
        lock.lock();
        lock.lock();
        System.out.println(Thread.currentThread().getId() + "set");
        get();
        lock.unlock();
        lock.unlock();
    }
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId() + "get");
        lock.unlock();
    }
}
