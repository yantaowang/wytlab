package leetcode;

public class SixSulution {

    //https://leetcode-cn.com/problems/longest-common-subsequence/solution/shi-pin-jiang-jie-shi-yong-dong-tai-gui-hua-qiu-ji/
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char ch1 = text1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                char ch2 = text2.charAt(j-1);
                if(ch2 == ch1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    //https://leetcode-cn.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums) {
        int[] f = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            f[i]  = 1;
        }
        for (int i = 1; i < nums.length ; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j]+1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < f.length; i++) {
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0, ans = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] fmax = new int[n];
        int[] fmin = new int[n];
        fmax[0] = nums[0];
        fmin[0] = nums[0];
        for (int i = 1; i < n; i++) {
           fmax[i] = Math.max(Math.max(fmax[i-1] * nums[i], fmin[i-1] * nums[i]), nums[i]);
            fmin[i] = Math.min(Math.min(fmax[i-1] * nums[i], fmin[i-1] * nums[i]), nums[i]);
        }

        int ans = fmax[0];
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, fmax[i]);
        }
        return ans;
    }

    public int climbStairs(int n) {
        int p = 0, q= 0, r = 1;
        for (int i = 1; i <=n ; i++) {
            p = q;
            q = r;
            r = p +q;
        }
        return r;
    }

    public static void main(String[] args) {
        longestCommonSubsequence("fsfs","dd");
    }
}
