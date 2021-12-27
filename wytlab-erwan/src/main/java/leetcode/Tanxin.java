package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tanxin {

    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            ans += Math.max(prices[i]-prices[i-1], 0);
        }
        return ans;
    }

    public int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        for (int i = 0; i < g.length; i++) {
            while (j < s.length && s[j] < g[i]) j++;
            if(j < s.length) {
                j++;
                ans++;
            }
        }
        return ans;
    }

    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(5, 0);
        map.put(10,0);
        map.put(20, 0);

        for(int bill : bills) {
            Integer val = map.get(bill);
            map.put(bill,val+1);
            if(!canexchange(bill - 5, map)) {
                return false;
            }
        }
        return true;
    }
    public boolean canexchange(int amount, Map<Integer, Integer> map) {
        int[] arr = new int[]{20,10,5};
        for (int i = 0; i < arr.length; i++) {
            while (amount >= arr[i] && map.get(arr[i]) > 0) {
                Integer val = map.get(arr[i]);
                map.put(arr[i],val-1);
                amount -= arr[i];
            }
        }
        return amount == 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,5,10,10,20};
        Tanxin tanxin = new Tanxin();
        System.out.println(tanxin.lemonadeChange(arr));
    }
}
