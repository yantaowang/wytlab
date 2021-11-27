package leetcode;

import java.util.*;

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

    //https://leetcode-cn.com/problems/minimum-genetic-mutation/
    public int minMutation(String start, String end, String[] bank) {
        List<String> list = Arrays.asList(bank); //存储合法的基因序列

        Deque<String> deque = new ArrayDeque<>(); //广度优先队列，存储start字符串每个下标字符变化时的新字符串
        //使用map的原因是假设原基因序列为AGCT....，变化第一个字符后为CGCT....,GGCT....，TGCT....，并且将这三个基因序列加入队列
        //下次取出队头元素CGCT....时，变化第一个字符后为GGCT....,AGCT....,TGCT....，这时判断出map中已经存在AGCT....,GGCT....,
        //TGCT....,就避免了重复计算，也可同时得到从一个字符串变到另一个字符串的最少变化次数
        Map<String, Integer> map = new HashMap<>(); //记录start字符串每个下标字符变化时的新字符串以及经过多少次变化可以得到西新
        String[] gene = {"A", "T", "C", "G"};
        deque.add(start);
        map.put(start, 0);
        while(!deque.isEmpty()) {
            String s = deque.poll(); //取队头元素

            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 4; j++) {
                    if(String.valueOf(s.charAt(i)).equals(gene[j])) continue; //如果s下标为i的字符与gene下标为j的字符相同则进行下一次循环

                    String ns = s.substring(0, i) + gene[j] + s.substring(i + 1, s.length()); //变化一个位置的字符后形成新的字符串
                    // System.out.println(ns);
                    if(!list.contains(ns)) continue; //如果合法基因库中不包含变化后的新字符串则进入下一次循环

                    if(!map.containsKey(ns)) {
                        map.put(ns, map.get(s) + 1);
                        deque.add(ns);
                        if(ns.equals(end)) return map.get(ns);
                    }
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        SevenSolution solution = new SevenSolution();
        System.out.println(solution.letterCombinations(""));
    }
}
