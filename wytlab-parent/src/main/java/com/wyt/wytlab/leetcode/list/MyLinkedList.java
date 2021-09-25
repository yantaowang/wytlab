package com.wyt.wytlab.leetcode.list;

public class MyLinkedList {
    private ListNode head;

    public ListNode getHead() {
        return head;
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (head == null) {
            return -1;
        }
        ListNode cur = head;
        int t_index = 0;
        while (cur != null) {
            if (t_index == index) {
                return cur.val;
            }
            cur = cur.next;
            t_index++;
        }
        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        } else {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        ListNode node = new ListNode(val);
        if (index <= 0) {
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
            return;
        }
        ListNode cur = head;
        int curIndex = 0;
        while (cur != null) {
            if (curIndex == index - 1) {
                node.next = cur.next;
                cur.next = node;
                break;
            }
            cur = cur.next;
            curIndex++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (head == null) {
            return;
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        int cur = 0;
        ListNode temp = head;
        while (temp.next != null) {
            if (cur == index - 1) {
                temp.next = temp.next.next;
                break;
            }
            cur++;
            temp = temp.next;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        int index = 1, cur = 0, pre = 0;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            index++;
        }
        cur = index - n;
        if (cur == 0) {
            if (head.next != null) {
                head = head.next;
                return head;
            }
            return null;
        }
        cur--;
        temp = head;
        while (pre < cur) {
            temp = temp.next;
            pre++;
        }
        if (pre == cur) {
            if (temp.next.next != null) {
                ListNode temp1 = temp;
                temp.next = temp.next.next;
            } else {
                temp.next = null;
            }
        }
        return head;
    }

    public ListNode mytest(ListNode head1) {
//        ListNode temp = head;
        head1 = head1.next;
        return head1;
    }

    public ListNode mytest1() {
        ListNode temp = head;
        temp = temp.next;
//        head.next = null;
        head = temp;
        return temp;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && slow != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //如果满足下面的条件，说明无环
        if (fast == null || fast.next == null) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinal = new ListNode(0);
        sentinal.next = head;
        ListNode pre = sentinal, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return sentinal.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode header = new ListNode();
        ListNode cur = header;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return header.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode header = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            if(header == null) {
                header = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            carry = sum / 10;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry > 0) {
            tail.next = new ListNode(carry);
        }
        return header;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++)
            former = former.next;
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}