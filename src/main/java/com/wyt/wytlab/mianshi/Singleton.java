package com.wyt.wytlab.mianshi;

public class Singleton {
    private static volatile Singleton instance = null;
    private Singleton() {
        System.out.println("this is a singleton");
    }
    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
