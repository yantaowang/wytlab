package com.wyt.wytlab.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class TestWeakReferece {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        o1= null;

        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.gc();
        Thread.sleep(1000);
        System.out.println("1111111111111111111111111");
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
