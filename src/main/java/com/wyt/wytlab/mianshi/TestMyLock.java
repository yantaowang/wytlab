package com.wyt.wytlab.mianshi;

public class TestMyLock {
    public static void main(String[] args) throws InterruptedException {
        SpintLock spintLock = new SpintLock();

        new Thread(()->{
            spintLock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spintLock.unlock();
        }).start();

        Thread.sleep(1000);

        new Thread(()->{
            spintLock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spintLock.unlock();
        }).start();
    }

}
