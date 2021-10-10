package leetcode;

import java.util.*;

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

    public int numberOfSubarrays(int[] nums, int k) {

    }
}
