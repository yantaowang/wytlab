package com.wyt.wytlab.leetcode;

import java.util.*;

public class Solution {

    //LeetCode 1109 航班预订统计
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n + 2];
        Arrays.fill(delta, 0);
        for (int[] booking : bookings) {
            int fir = booking[0];
            int last = booking[1];
            int seats = booking[2];
            delta[fir] += seats;
            delta[last+1] -= seats;
        }

        int[] a = new int[n+1];
        a[0] = 0;
        for (int i = 1; i <= n ; i++) {
            a[i] = a[i-1] + delta[i];
        }

        int[] ans = new int[n];
        for (int i = 1; i <= n ; i++) {
            ans[i-1] = a[i];
        }
        return ans;
    }

    // LeetCode 167 两数之和 - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        int j = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            while (i < j && numbers[i] + numbers[j] > target) {
                j--;
            }
            if(i < j && numbers[i] + numbers[j] == target) {
                return new int[]{i+1, j+1};
            }
        }
        return null;
    }

    // LeetCode 239 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && q.getFirst() <= i - k) {
                q.removeFirst();
            }
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
                q.removeLast();
            }
            q.addLast(i);
            if (i >= k - 1) ans[i - (k - 1)] = nums[q.getFirst()];
        }
        return ans;
    }

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        num = new int[n];
        for (int i = 0; i < n; i++) {
           num[i] = nums[i];
        }
        used = new boolean[n];
        per = new ArrayList<>();
        ans = new ArrayList<List<Integer>>();
        dfs(0);
        return ans;
    }

    private void dfs(int depth) {
        if(depth == n) {
            ans.add(new ArrayList<>(per));
            return;
        }
        for (int i = 0; i < n; i++) {
            if(used[i]) continue;
            used[i] = true;
            per.add(num[i]);
            dfs(depth + 1);
            per.remove(per.size() - 1);
            used[i] = false;
        }
    }

    private int n;
    private int[] num;
    private boolean[] used;
    private List<Integer> per;
    private List<List<Integer>> ans;
}
