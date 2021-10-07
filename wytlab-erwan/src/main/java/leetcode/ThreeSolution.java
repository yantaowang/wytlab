package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ThreeSolution {
    //两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return nums;
    }
    //模拟行走机器人
    public int robotSim(int[] commands, int[][] obstacles) {

    }

    private
}
