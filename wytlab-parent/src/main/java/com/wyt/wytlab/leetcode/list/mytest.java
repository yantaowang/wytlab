package com.wyt.wytlab.leetcode.list;

import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class mytest {
    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        f(person);
        StringBuffer ss = new StringBuffer();
        ss.append("www");
        StringBuilder sb = new StringBuilder();
        sb.append("ddd");
        System.out.println(ss.toString() + sb.toString());

        Vector vector = new Vector();
        vector.add(1);
        vector.add(1);
        System.out.println(vector.toArray());

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(1);
        queue.add(1);
        queue.remove();
        queue.poll();

        System.out.println("wang".charAt(3) - '0');
        System.out.println("wang".charAt(3));
    }

    public static void f(Person p1) {
        p1.setAge(11);
    }
}
