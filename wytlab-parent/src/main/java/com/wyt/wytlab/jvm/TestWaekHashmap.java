package com.wyt.wytlab.jvm;

import java.util.WeakHashMap;

public class TestWaekHashmap {
    public static void main(String[] args) {
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(new Integer(1),"www");
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap);
    }
}
