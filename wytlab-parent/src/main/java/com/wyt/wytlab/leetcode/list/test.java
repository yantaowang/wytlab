package com.wyt.wytlab.leetcode.list;

public class test {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(2);
        linkedList.addAtTail(4);//链表变为1-> 2-> 3

        MyLinkedList linkedList1 = new MyLinkedList();
        linkedList1.addAtHead(1);
        linkedList1.addAtTail(3);
        linkedList1.addAtTail(4);//链表变为1-> 2-> 3
//        linkedList.get(1);            //返回2
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        linkedList.get(1);            //返回3
//        linkedList.removeNthFromEnd(linkedList.getHead(),2);
//        linkedList.mytest(linkedList.getHead());
//        linkedList.mytest1();
//        MyLinkedList.ListNode  head = linkedList.removeElements(linkedList.getHead(),3);
//        MyLinkedList.ListNode pre = linkedList.reverseList(linkedList.getHead());

        MyLinkedList.ListNode listNode = linkedList.mergeTwoLists(linkedList.getHead(), linkedList1.getHead());
    }
    private static synchronized void say() {
        System.out.println(111);
    }
}
