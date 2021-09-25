package com.wyt.wytlab.leetcode.tree;

import java.util.PriorityQueue;

public class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if(pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(2);
        priorityQueue.offer(1);
        priorityQueue.offer(3);
        System.out.println(priorityQueue.peek());
        priorityQueue.poll();
        System.out.println(priorityQueue.peek());
        priorityQueue.poll();
        System.out.println(priorityQueue.peek());
    }
}
