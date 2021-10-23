package leetcode;

import java.util.ArrayList;
import java.util.List;

//递归
public class FourSolution {

    //https://leetcode-cn.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        recur(nums, 0);
        return ans;
    }
    private void recur(int[] nums, int i) {
        if(i == n) {
            ans.add(new ArrayList<Integer>(chosen));
            return;
        }
        recur(nums,i+1);
        chosen.add(nums[i]);
        recur(nums, i+1);
        chosen.remove(chosen.size()-1);
    }
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> chosen = new ArrayList<>();
    private int n;

    //递归源码
//    void recursion(int level, int param) {
//// terminator
//        if (level > MAX_LEVEL) {
//// process result
//            return; }
//// process logic in current level
//        process(level, param);
//// drill down
//        recur(level + 1, new_param);
//// restore the current level status
//    }
}
