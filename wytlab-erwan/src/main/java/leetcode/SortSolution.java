package leetcode;

import java.util.Random;

/**
 * 排序
 */
public class SortSolution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int l, int r) {
        if(l >= r) return;
        int priovt = partition(nums,l,r);
        quickSort(nums, l, priovt);
        quickSort(nums, priovt + 1, r);
    }
    public int partition(int[] nums, int l, int r) {
        int priovt = l + new Random().nextInt(nums.length - 1) % (r-l+1);
        int val = nums[priovt];
        while(l < r){
            while(nums[l] < val) l++;
            while(nums[r] > val) r--;
            if(l==r) break;
            if(l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;r--;
            }
        }
        return r;
    }
}
