package leetcode;

public class Donggui {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
    }

    // TODO: 2022/1/2
    public static int coinChange(int[] coins, int amount) {
        int INF = (int)1e9;
        int[] opt = new int[amount + 1];
        opt[0] = 0;
        for (int i = 1; i <= amount; i++) {
            opt[i] = INF;
            for (int j = 0; j < coins.length; j++)
                if (i - coins[j] >= 0)
                    opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1);
        }
        if (opt[amount] >= INF) opt[amount] = -1;
        return opt[amount];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
       int n = obstacleGrid.length, m = obstacleGrid[0].length;
       int[][] f = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
               if(obstacleGrid[i][j] == 1) f[i][j] = 0;
               else if(i == 0 && j == 0) f[i][j] = 1;
               else if(i == 0) f[i][j] = f[i][j-1];
               else if(j == 0) f[i][j] = f[i - 1][j];
               else f[i][j] = f[i][j-1] + f[i-1][j];
            }
        }
        return f[n-1][m-1];
    }
}
