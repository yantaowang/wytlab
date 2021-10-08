package leetcode;

import java.util.Stack;

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> mid;
    public MinStack() {
        stack = new Stack<Integer>();
        mid = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (mid.empty()) {
            mid.push(x);
            return;
        }
        mid.push(Math.min(x,mid.peek()));
    }

    public void pop() {
        stack.pop();
        mid.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mid.peek();
    }
}