package leetcode;

import java.util.*;

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

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr1.length;i++){
            list.add(arr1[i]);
        }
        Collections.sort(list, (x, y) -> {
            if(map.containsKey(x) || map.containsKey(y))
                return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
}
