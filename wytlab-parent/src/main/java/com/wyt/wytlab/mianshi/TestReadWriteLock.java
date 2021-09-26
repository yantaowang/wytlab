package com.wyt.wytlab.mianshi;

public class TestReadWriteLock {
    public static void main(String[] args) {
        MyCahe myCahe = new MyCahe();
        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(()->{
                myCahe.put(String.valueOf(t), t);
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(()->{
                myCahe.get(String.valueOf(t));
            },String.valueOf(i)).start();
        }
    }
}
