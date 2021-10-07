package leetcode;

import java.util.*;

public class Solution {
    //作业1
    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode cur = node;
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
        cur.next = l1 != null ? l1 : l2;
        return node.next;
    }
    //作业2
    //加1
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    //第一周
    public int removeDuplicates(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                nums[n++] = nums[i];
            }
        }
        return n;
    }

    public void moveZeroes(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[n++] = nums[i];
            }
        }
        while (n < nums.length - 1) {
            nums[n++] = 0;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            ListNode nextHead = head.next;
            head.next = last;
            last = head;
            head = nextHead;
        }
        return last;
    }

    //k个一组反转
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode protect = new ListNode(0, head);
        ListNode last = head;
        while (head != null) {
            //1、分组（往前走k-1步）， 开始head,结尾end
            ListNode end = getEnd(head, k);
            if (end == null) break;
            ListNode nextGroupHead = end.next;
            //2、一组内部反转（head和next）
            reverseList(head, nextGroupHead);
            //3、更新每一组前后的边
            last.next = end;
            head.next = nextGroupHead;

            last = head;
            head = nextGroupHead;
        }
        return protect.next;
    }

    public ListNode reverseList(ListNode head, ListNode stop) {
        ListNode last = head;
        head = head.next;
        while (head != stop) {
            ListNode nextHead = head.next;
            head.next = last;
            last = head;
            head = nextHead;
        }
        return last;
    }

    ListNode getEnd(ListNode head, int k) {
        while (head != null) {
            k--;
            if (k == 0) return head;
            head = head.next;
        }
        return null;
    }

    //有效的括号
    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(String.valueOf(c));
            } else {
                if (stack.empty()) {
                    stack.push(String.valueOf(c));
                    continue;
                }
                char ch = stack.pop().charAt(0);
                if (c == ')' && ch != '(') {
                    return false;
                }
                if (c == '}' && ch != '{') {
                    return false;
                }
                if (c == ']' && ch != '[') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    //逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                Integer s2 = stack.pop();
                Integer s1 = stack.pop();
                stack.push(getVal(s1, s2, tokens[i]));
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.peek();
    }

    private Integer getVal(Integer s1, Integer s2, String token) {
        if (token.equals("+")) {
            return s1 + s2;
        }
        if (token.equals("-")) {
            return s1 - s2;
        }
        if (token.equals("*")) {
            return s1 * s2;
        }
        if (token.equals("/")) {
            return s1 / s2;
        }
        return 0;
    }

    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    //柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        Stack<Rect> stack = new Stack<>();
        int len = heights.length;
        int[] newArr = Arrays.copyOf(heights, len + 1);
        newArr[len] = 0;
        int ans = 0;
        for (int i = 0; i < newArr.length; i++) {
            int realWith = 0;
            while (!stack.empty() && stack.peek().height > newArr[i]) {
                realWith += stack.peek().width;
                ans = Math.max(ans, stack.peek().height * realWith);
                stack.pop();
            }
            Rect rect = new Rect();
            rect.height = newArr[i];
            rect.width = realWith + 1;
            stack.push(rect);
        }
        return ans;
    }

    private class Rect {
        public int height;
        public int width;
    }

    //接雨水
    public int trap(int[] height) {
        Stack<Rect> stack = new Stack<>();
        int ans = 0;
        //1
        for (int i = 0; i < height.length; i++) {
            int realWidth = 0;
            while (!stack.empty() && stack.peek().height <= height[i]) {
                realWidth += stack.peek().width;
                int bottom = stack.peek().height;
                stack.pop();
                //验证空，水从左边溜走了
                if (stack.empty()) continue;
                int up = Math.min(stack.peek().height, height[i]);
                ans += realWidth * (up - bottom);
            }
            Rect rect = new Rect();
            rect.height = height[i];
            rect.width = realWidth + 1;
            stack.push(rect);
        }
        return ans;
    }

    //滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            //取答案
            if (i >= k - 1) {
                ans[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return ans;
    }
}
