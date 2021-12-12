package leetcode;

public class Erfen {

    public int findMin(int[] nums) {
      int left = 0, right = nums.length - 1;
      while (left < right) {
          int mid = (right + left) / 2;
          if(nums[mid] <= nums[right]) {
              right = mid;
          } else {
              left = mid + 1;
          }
      }
      return nums[right];
    }

    public int[] searchRange(int[] nums, int target) {
        //开始位置：第一个>=target的位置
        //结束位置：第一个<=target的位置
        int[] ans = new int[2];
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans[0] = right;

        left = -1; right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if(nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans[1] = left;

        if(ans[0] > ans[1]) return new int[]{-1,-1};
        return ans;
    }

    public int mySqrt(int x) {
       //找最大的ans，满足ans*ans<=x;
        int left = 1, right = x;
        while (left < right) {
            int mid = (right + left + 1) / 2;
            if(mid <= x / mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int findPeakElement(int[] nums) {
       int left = 0, right = nums.length - 1;
       while (left < right) {
           int lmid = (left + right) / 2;
           int rmid = lmid + 1;
           if(nums[lmid]< nums[rmid]) {
               left = lmid +1;
           } else {
               right = rmid - 1;
           }
       }
       return right;
    }

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for(int num: nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if(validate(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    boolean validate(int[] nums, int m, int size) {
        int box = 0;
        int count = 1;
        for(int num : nums) {
            if(box + num <= size) {
                box += num;
            } else {
                count++;
                box = num;
            }
        }
        return count <= m;
    }
}
