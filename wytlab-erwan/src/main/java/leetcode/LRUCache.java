package leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/lru-cache/
public class LRUCache {

    public LRUCache(int capacity) {
       this.capacity = capacity;
       head = new Node();
       tail = new Node();
       head.next = tail;
       tail.pre = head;
       map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insert(head,node);
        return node.value;
    }

    public void put(int key, int value) {
       if(!map.containsKey(key)) {
           Node node = new Node();
           node.key = key;
           node.value = value;
           map.put(key, node);
           insert(head, node);
           if(map.size() > capacity) {
               map.remove(tail.pre.key);
               remove(tail.pre);
           }

       } else {
           Node node = map.get(key);
           node.value = value;
           remove(node);
           insert(head, node);
       }
    }

    private class Node{
        private int key;
        private int value;
        private Node pre;
        private Node next;
    }

    private Map<Integer, Node> map;

    private Node head;
    private Node tail;
    private int capacity;

    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void insert(Node p, Node node) {
        p.next.pre = node;
        node.next = p.next;
        p.next = node;
        node.pre = p;
    }
}
