package leetcode;

public class Solution {
    //第一周
    public int removeDuplicates(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i==0 || nums[i-1] != nums[i]) {
                nums[n++] =  nums[i];
            }
        }
        return n;
    }

    public void moveZeroes(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[n++] =  nums[i];
            }
        }
        while (n < nums.length -1) {
            nums[n++] = 0;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
       int i = m -1;
       int j = n - 1;
       for(int k = m + n -1; k>=0; k--) {
           if(j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
               nums1[k] = nums1[i];
               i--;
           } else {
               nums1[k] = nums2[j];
               j--;
           }
       }
    }
}
