package com.wyt.wytlab.java;

public class InheritableThreadLocalExample {
    // 创建一个InheritableThreadLocal变量
    public static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal threadLocal11 = new ThreadLocal();

    public static void main(String[] args) {
        threadLocal11.set(1000);
        // 线程1
        new Thread(() -> {
//            threadLocal11.set(100); // 设置线程1的局部变量值
//            System.out.println("Thread 1 value: " + threadLocal.get()); // 输出线程1的值
            System.out.println(threadLocal11.get());
            // 线程2
            new Thread(() -> {
//                threadLocal.set(200); // 设置线程2的局部变量值
                System.out.println("Thread 2 value: " + threadLocal.get()); // 输出线程2的值
            }).start();
        }).start();
    }
}