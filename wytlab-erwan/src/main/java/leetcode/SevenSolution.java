package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SevenSolution {
    //https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        map = new HashMap<>();
        ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        dfs(digits, 0, "");
        return ans;
    }

    private void dfs(String digits, Integer index, String str) {
        if (index == digits.length()) {
            ans.add(str);
            return;
        }
        for (int i = 0; i < map.get(String.valueOf(digits.charAt(index))).length(); i++) {
            char ch = map.get(String.valueOf(digits.charAt(index))).charAt(i);
            dfs(digits, index + 1, str + ch);
        }
    }
    private List<String> ans;
    private Map<String, String> map;

    public static void main(String[] args) {
        SevenSolution solution = new SevenSolution();
        System.out.println(solution.letterCombinations(""));
    }
}
