package leetcode;

import java.util.*;

public class ThreeSolution {
    //数组的度（作业）
    //https://leetcode-cn.com/problems/degree-of-an-array/
    public int findShortestSubArray(int[] nums) {
        Map<Integer,Integer> numMap = new HashMap<>();
        Map<Integer,Integer> indexMap = new HashMap<>();
        int count = 0;
        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            if(numMap.containsKey(nums[i])) {
                if(numMap.get(nums[i]) + 1 > count || (numMap.get(nums[i]) + 1 == count && i - indexMap.get(nums[i]) + 1 < ans)) {
                    count = numMap.get(nums[i]) + 1;
                    ans = i - indexMap.get(nums[i]) + 1;
                    numMap.put(nums[i], count);
                } else {
                    numMap.put(nums[i], numMap.get(nums[i]) + 1);
                }
            } else {
                numMap.put(nums[i], 1);
                indexMap.put(nums[i], i);
            }
        }
        return ans;
    }
    //和为 K 的子数组(作业)
    //https://leetcode-cn.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        s[0] = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 1; i <= n ; i++) {
            s[i] = s[i-1] + nums[i - 1];
            if(map.containsKey(s[i] - k)) {
                ans += map.get(s[i] - k);
            }
            map.put(s[i], map.getOrDefault(s[i], 0) + 1);
        }
        return ans;
    }
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
        Set<String> set = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            set.add(getSetVal(obstacles[i]));
        }
        int dir = 0, ans = 0;
        int x = 0, y = 0;
        //方向数组
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for(int command : commands) {
            if(command == -1) {
                // TODO: 2021/10/7
                dir = (dir + 1) % 4;
            } else if (command == -2) {
                // TODO: 2021/10/7
                dir = (dir + 3) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(set.contains(getSetVal(new int[]{nx, ny}))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }

    private String getSetVal(int[] obs) {
        return obs[0] + "-" + obs[1];
    }

    //https://leetcode-cn.com/problems/group-anagrams/  49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
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

    //https://leetcode-cn.com/problems/count-number-of-nice-subarrays/submissions/ 优美子数组  ******
    public int numberOfSubarrays(int[] nums, int k) {
        int[] s = new int[nums.length + 1];
        for (int i = 1; i <= nums.length ; i++) {
            s[i] = s[i - 1] + nums[i -1] % 2;
        }
        int[] count = new int[nums.length + 1];
        int ans = 0;
        count[s[0]]++;
        for (int i = 1; i <= nums.length; i++) {
            if(s[i] - k >= 0) {
                ans += count[s[i] - k];
            }
            count[s[i]]++;
        }
        return ans;
    }

    //https://leetcode-cn.com/problems/maximum-subarray/ 最大子序和
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        int[] premin = new int[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n ; i++) {
            s[i] = s[i-1] + nums[i -1];
        }

        premin[0] = s[0];
        for (int i = 1; i <= n ; i++) {
            premin[i] = Math.min(premin[i-1], s[i]);
        }
        int ans = -1000000;
        for (int i = 1; i <= n ; i++) {
            ans = Math.max(ans, s[i] - premin[i-1]);
        }
        return ans;
    }


}
