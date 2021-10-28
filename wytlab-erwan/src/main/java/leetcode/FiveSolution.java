package leetcode;

import javafx.util.Pair;

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

    //https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
    public List<Integer> preorder(Node root) {
       List<Integer> list = new ArrayList<>();
       if(root == null) return list;
       Stack<Node> stack = new Stack<>();
       stack.push(root);
       while (!stack.isEmpty()) {
           Node cur = stack.pop();
           list.add(cur.val);
           for (int i = cur.children.size() - 1; i >= 0 ; i--) {
              stack.push(cur.children.get(i));
           }
       }
       return list;
    }

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.peek().getKey();
            Integer depth = queue.poll().getValue();
            if(depth >= queue.size()) list.add(new ArrayList<>());
            list.get(depth).add(cur.val);
            for (int i = 0; i < cur.children.size(); i++) {
                queue.add(new Pair<>(cur.children.get(i), depth + 1));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FiveSolution fiveSolution = new FiveSolution();
        System.out.println(fiveSolution.myPow(2,10));
        System.out.println(-(1l<<31));
        System.out.println(fiveSolution.generateParenthesis(1));
    }
}
