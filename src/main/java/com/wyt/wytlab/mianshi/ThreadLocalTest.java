package com.wyt.wytlab.mianshi;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("1");
        System.out.println(threadLocal.get());
        threadLocal.set("2");
        System.out.println(threadLocal.get());
    }
}
