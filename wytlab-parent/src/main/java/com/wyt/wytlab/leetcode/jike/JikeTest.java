package com.wyt.wytlab.leetcode.jike;

public class JikeTest {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,2,2,3}));
    }

    public static int removeDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i] != nums[j] && j > i+1) {
                    nums[++i] = nums[j];
                    break;
                }
            }
            if(i < nums.length -1 && nums[i+1] < nums[i]) {
                return i + 1;
            }
        }
        return nums.length;
    }
}
