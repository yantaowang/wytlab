package com.wyt.wytlab.leetcode.queue;

public class main {
    public static void main(String[] args) {
//        MyQueue q = new MyQueue();
//        q.enQueue(5);
//        q.enQueue(3);
//        if (q.isEmpty() == false) {
//            System.out.println(q.Front());
//        }
//        q.deQueue();
//        if (q.isEmpty() == false) {
//            System.out.println(q.Front());
//        }
//        q.deQueue();
//        if (q.isEmpty() == false) {
//            System.out.println(q.Front());
//        }
        MyCircularQueue obj = new MyCircularQueue(10);
        boolean param_1 = obj.enQueue(1);
        boolean param_2 = obj.deQueue();
        int param_3 = obj.Front();
        int param_4 = obj.Rear();
        boolean param_5 = obj.isEmpty();
        boolean param_6 = obj.isFull();
    }

}