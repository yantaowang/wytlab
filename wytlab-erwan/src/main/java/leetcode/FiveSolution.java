package leetcode;

import java.util.*;

public class FiveSolution {
    //https://leetcode-cn.com/problems/powx-n/
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == -(1l << 31)) return 1.0 / (myPow(x, -(n+1))*x);
        if(n < 0) return 1.0 / myPow(x, -n);
        double temp = myPow(x, n/2);
        double ans = temp * temp;
        if(n%2==1) ans *= x;
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        if (n == 0) return Arrays.asList("");
        if(map.containsKey(n)) return map.get(n);
        List<String> ans = new ArrayList<>();
        for (int k = 1; k <= n; k++) {
            List<String> aList = generateParenthesis(k - 1);
            List<String> bList = generateParenthesis(n - k);
            for (String a : aList) {
                for (String b : bList) {
                    ans.add("(" + a + ")" + b);
                }
            }
        }
        map.put(n, ans);
        return ans;
    }

    private Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
        FiveSolution fiveSolution = new FiveSolution();
        System.out.println(fiveSolution.myPow(2,10));
        System.out.println(-(1l<<31));
        System.out.println(fiveSolution.generateParenthesis(1));
    }
}
