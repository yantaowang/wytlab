package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
       return  0;

    }

    //https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/submissions/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        wordsMap = new HashMap<>();
        int total = 0;
        for (String word : words) {
            total += word.length();
            wordsMap.put(word, wordsMap.get(word) == null ? 1 : wordsMap.get(word) + 1);
        }

        for (int i = 0; i + total <= s.length(); i++) {
            if(validate(s.substring(i, i + total), words)) {
                ans.add(i);
            }
        }

        return ans;
    }

    private boolean validate(String s, String[] words) {
        int len = words[0].length();
        Map<String, Integer> splitMap = new HashMap<>();
        for (int i = 0; i < s.length() ; i += len) {
            String str = s.substring(i, i + len);
            splitMap.put(str, splitMap.get(str) == null ? 1 : splitMap.get(str) + 1);
        }
        return equalsMap(wordsMap, splitMap);
    }

    private boolean equalsMap(Map<String, Integer> src, Map<String, Integer> des) {
        for(Map.Entry<String, Integer> entry : src.entrySet()) {
            if(!des.containsKey(entry.getKey()) || !entry.getValue().equals(des.get(entry.getKey()))) {
                return false;
            }
        }
        for(Map.Entry<String, Integer> entry : des.entrySet()) {
            if(!src.containsKey(entry.getKey()) || !entry.getValue().equals(src.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private  Map<String, Integer> wordsMap;

    //https://leetcode-cn.com/problems/corporate-flight-bookings/ 航班预订统计 ************
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n+2];
        for (int[] booking:bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            delta[first] += seats;
            delta[last+1] -= seats;
        }
        int[] sum = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i-1] + delta[i];
        }
        int[] answer = new int[n];
        for (int i = 1; i <= n ; i++) {
            answer[i-1] = sum[i];
        }
        return answer;
    }

    //https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum1(int[] numbers, int target) {
        int j = numbers.length-1;
        for (int i = 0; i < numbers.length; i++) {
            while (i < j && numbers[i] + numbers[j] > target) j--;
            if(i< j && numbers[i] + numbers[j] == target) {
                return new int[]{i+1, j+1};
            }
        }
        return null;
    }
}
