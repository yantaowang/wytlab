package leetcode;

import java.util.*;

public class ThreeSolution {
    //数组的度（作业）  时间复杂度O(n), 空间复杂度O(n)
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
    //和为 K 的子数组(作业)   时间复杂度O(n), 空间复杂度O(n)
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

    private  Map<String, Integer> wordsMap;

    //https://leetcode-cn.com/problems/count-number-of-nice-subarrays/submissions/ 优美子数组 前缀和、数组计数代码模板
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

    //https://leetcode-cn.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> jks = twoSumNew(nums, i + 1, -nums[i]);
            for (List<Integer> list: jks) {
                ans.add(Arrays.asList(nums[i], list.get(0),list.get(1)));
            }
        }
        return ans;
    }
    //https://leetcode-cn.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j-i));
            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    public List<List<Integer>> twoSumNew(int[] numbers, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int j = numbers.length-1;
        for (int i = start; i < numbers.length; i++) {
            while (i < j && numbers[i] + numbers[j] > target) j--;
            if(i< j && numbers[i] + numbers[j] == target) {
                ans.add(Arrays.asList(numbers[i], numbers[j]));
            }
        }
        return ans;
    }

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }


    public List<List<Integer>> combine(int n, int k) {
        ans1 = new ArrayList<>();
        as = new ArrayList<>();
        dfs(1, n, k);
        return ans1;
    }
    private List<List<Integer>> ans1;
    List<Integer> as;
    private void dfs(int cur, int n, int k) {
        if(as.size() + n-cur+1 < k) {
            return;
        }
        if(as.size() == k) {
            ans1.add(new ArrayList<>(as));
            return;
        }
        as.add(cur);
        dfs(cur+1, n, k);
        as.remove(as.size()-1);
        dfs(cur+1,n,k);
    }

    public List<List<Integer>> permute(int[] nums) {
       ans2 = new ArrayList<>();
       a = new ArrayList<>();
       used = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            used.add(false);
        }
       recur(nums, 0);
       return ans2;
    }
    public void recur(int[] nums, int pos) {
        if(pos == nums.length) {
            ans2.add(new ArrayList<>(a));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used.size() > 0 && !used.get(i)) {
                a.add(nums[i]);
                used.set(i, true);
                recur(nums, pos+1);
                used.set(i, false);
                a.remove(a.size()-1);
            }
        }
    }
    private List<List<Integer>> ans2;
    List<Integer> a;
    private List<Boolean> used;

    public TreeNode invertTree(TreeNode root) {
       if(root == null) {
           return null;
       }
       TreeNode temp = root.left;
       root.left = root.right;
       root.right = temp;
       invertTree(root.left);
       invertTree(root.right);
       return root;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            int maxLeft = maxDepth(root.left);
            int maxRight = maxDepth(root.right);
            return Math.max(maxLeft, maxRight) + 1;
        }
    }
}
