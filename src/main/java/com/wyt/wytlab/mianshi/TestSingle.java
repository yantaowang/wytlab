package com.wyt.wytlab.mianshi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestSingle {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                Singleton.getInstance();
//            },String.valueOf(i)).start();
//        }
        AtomicInteger val = new AtomicInteger(5);
        System.out.println(val.compareAndSet(5, 11));
        System.out.println(val.compareAndSet(11, 5));

        System.out.println("**************************************");
        User z3 = new User("z3", 23);
        User li4 = new User("li4", 35);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4)+ atomicReference.get().toString());
        atomicReference.compareAndSet(z3,li4);
        System.out.println(atomicReference.get().toString());

        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);
        atomicStampedReference.compareAndSet(1,5,1,2);
        System.out.println(atomicStampedReference.getReference());

        new ArrayList<Integer>().add(1);
    }
}
