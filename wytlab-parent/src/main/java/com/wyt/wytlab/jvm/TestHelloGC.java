package com.wyt.wytlab.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class TestHelloGC {
    public static void main(String[] args) {
        System.out.println("hello gc");
//        try {
//////////            Thread.sleep(Integer.MAX_VALUE);
//////////        } catch (InterruptedException e) {
//////////            e.printStackTrace();
//////////        }
        Object o1 = new Object();
        WeakReference<Object> o2 = new WeakReference<>(o1);
        System.out.println(o2.get());
        try {
            o1 = null;
            System.gc();
            System.out.println(o2.get());

//            Byte[] bytes = new Byte[30 * 1024 * 1024];
        } finally {
        }

    }
}
