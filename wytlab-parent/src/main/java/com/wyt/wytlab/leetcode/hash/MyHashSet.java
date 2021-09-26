package com.wyt.wytlab.leetcode.hash;

import java.util.Arrays;

public class MyHashSet {
    int []arr=null;
    /** Initialize your data structure here. */
    public MyHashSet() {

        arr=new int[100001];
        Arrays.fill(arr,-1);

    }

    public void add(int key) {

        arr[key]=key;
    }

    public void remove(int key) {

        arr[key]=-1;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {

        if (arr[key]!=key)
            return false;
        return true;
    }
}
