package com.wyt.wytlab.work;

public class ThreadDemo {
    public static volatile int num= 1;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<=100) {
                    if(num % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() + num);
                        num++;
                    }
                }
            }
        }, "thread1:").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<=100) {
                    if(num % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + num);
                        num++;
                    }
                }
            }
        },"thread2:").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<=100) {
                    if(num % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + num);
                        num++;
                    }
                }
            }
        },"thread3:").start();
        while (num <=100) {
        }
        System.out.println("打印完毕");
    }
}
