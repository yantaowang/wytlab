package leetcode;

import javafx.util.Pair;

import java.util.*;

public class FiveSolution {
    //https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        return build1(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }
    public TreeNode build1(int[] inorder, int inStart, int inEnd,
                          int[] postorder, int postStart, int postEnd) {
        // base case
        if (inStart > inEnd) {
            return null;
        }

        // root 节点对应的值是后序遍历数组的最后一个元素
        int rootVal = postorder[postEnd];
        // 找到 root 在中序遍历中的位置
        int index = 0;
        while (inorder[index] != postorder[postEnd]) index++;
        // 先构造出根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build1(inorder, inStart, index - 1,
                postorder, postStart, postStart + index - inStart - 1);
        root.right = build1(inorder, index + 1, inEnd,
                postorder, postStart + index - inStart, postEnd - 1);

        // 返回根节点
        return root;
    }
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

//    //https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
//    public List<Integer> preorder(Node root) {
//       List<Integer> list = new ArrayList<>();
//       if(root == null) return list;
//       Stack<Node> stack = new Stack<>();
//       stack.push(root);
//       while (!stack.isEmpty()) {
//           Node cur = stack.pop();
//           list.add(cur.val);
//           for (int i = cur.children.size() - 1; i >= 0 ; i--) {
//              stack.push(cur.children.get(i));
//           }
//       }
//       return list;
//    }

//    public List<List<Integer>> levelOrder(Node root) {
//        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
//        List<List<Integer>> list = new ArrayList<>();
//        if(root == null) return list;
//        queue.add(new Pair<>(root, 0));
//        while (!queue.isEmpty()) {
//            Node cur = queue.peek().getKey();
//            Integer depth = queue.poll().getValue();
//            if(depth >= queue.size()) list.add(new ArrayList<>());
//            list.get(depth).add(cur.val);
//            for (int i = 0; i < cur.children.size(); i++) {
//                queue.add(new Pair<>(cur.children.get(i), depth + 1));
//            }
//        }
//        return list;
//    }

    //https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }
    private void dfs(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(ans, root.left);
        ans.add(root.val);
        dfs(ans, root.right);
    }

    //https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/submissions/
    public List<Integer> preorder(Node root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            ans.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.add(node.children.get(i));
            }
        }
        return ans;
    }

    //https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0)); 
        while (!queue.isEmpty()) {
            Node node = queue.peek().getKey();
            Integer depth = queue.poll().getValue();
            if(depth >= ans.size()) ans.add(new ArrayList<>());
            ans.get(depth).add(node.val);
            for (int i = 0; i < node.children.size(); i++) {
                queue.add(new Pair<Node, Integer>(node.children.get(i), depth + 1));
            }
        }
        return ans;
    }

    //https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int l1, int r1, int l2, int r2) {
        if(l1 > r1) return null;
        int mid = l2;
        while (inorder[mid] != preorder[l1]) mid++;
        TreeNode root = new TreeNode(preorder[l1]);
        root.left = build(l1 + 1, l1 + mid - l2, l2, mid - 1);
        root.right = build(l1 + mid - l2 + 1, r1, mid + 1, r2);
        return root;
    }

    private int[] preorder;
    private int[] inorder;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private TreeNode ans;

    //https://leetcode-cn.com/problems/course-schedule/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> to = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            to.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int ai = prerequisites[i][0];
            int bi = prerequisites[i][1];
            to.get(bi).add(ai);
            indeg[ai]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if(indeg[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            ans.add(course);
            for (int i = 0; i < to.get(course).size(); i++) {
                indeg[to.get(course).get(i)]--;
                if(indeg[to.get(course).get(i)] == 0) {
                    queue.add(to.get(course).get(i));
                }
            }
        }
        return ans.size() == numCourses;
    }

    //https://leetcode-cn.com/problems/course-schedule-ii/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> to = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            to.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int ai = prerequisites[i][0];
            int bi = prerequisites[i][1];
            to.get(bi).add(ai);
            indeg[ai]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if(indeg[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            ans.add(course);
            for (int i = 0; i < to.get(course).size(); i++) {
                indeg[to.get(course).get(i)]--;
                if(indeg[to.get(course).get(i)] == 0) {
                    queue.add(to.get(course).get(i));
                }
            }
        }

        if(ans.size() != numCourses) {
            return new int[0];
        } else {
            int[] aa = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                aa[i] = ans.get(i);
            }
            return aa;
        }
    }

    // TODO: 2021/11/7 有问题
    //https://leetcode-cn.com/problems/redundant-connection/
    List<List<Integer>> to = new ArrayList<>();
    boolean hasCycle = false;
    public int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for (int i = 0; i < edges.length; i++) {
            n = Math.max(n, Math.max(edges[i][0],edges[i][1]));
        }

        to = new ArrayList<>(n+1);
        for (int i = 0; i < n+1; i++) {
            to.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[n+1];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            to.get(x).add(y);
            to.get(y).add(x);

            hasCycle = false;
            for (int i1 = 1; i1 < visited.length; i1++) {
                visited[i] = false;
            }

            dfs(visited, x, 0);
            if(hasCycle) return edges[i];
        }
        return new int[0];
    }

    void dfs(boolean[] visited, int x, int fa) {
        visited[x] = true;
        for (int i = 0; i < to.get(x).size(); i++) {
            if(to.get(x).get(i) == fa) continue;
            if(!visited[to.get(x).get(i)]) {
                dfs(visited, to.get(x).get(i), x);
            } else {
                hasCycle = true;
            }
        }
    }

    //https://leetcode-cn.com/problems/n-queens/
    public List<List<String>> solveNQueens(int n) {
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);
        HashMap<Integer, Boolean> usedPlus = new HashMap<>();
        HashMap<Integer, Boolean> usedMinus = new HashMap<>();
        dfs(n, 0, used, usedPlus, usedMinus);

        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> p: result) {
            List<String> temp = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                char[] chs = new char[n];
                Arrays.fill(chs, '.');
                chs[p.get(row)] = 'Q';
                temp.add(new String(chs));
            }
            ans.add(temp);
        }
        return ans;
    }
    private List<Integer> p = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    void dfs(int n, int row, boolean[] used
            , HashMap<Integer, Boolean> usedPlus, HashMap<Integer, Boolean> usedMinus) {
        if(row == n) {
            result.add(new ArrayList<>(p));
            return;
        }
        for (int col = 0; col < n ; col++) {
            if(!used[col] && !usedPlus.getOrDefault(row+col, false)
            && !usedMinus.getOrDefault(row - col, false)) {
                p.add(col);
                used[col] = true;
                usedPlus.put(row + col, true);
                usedMinus.put(row - col, true);
                dfs(n, row + 1,used, usedPlus, usedMinus);
                used[col] = false;
                usedPlus.put(row + col, false);
                usedMinus.put(row - col, false);
                final int temp = col;
                p.removeIf(item -> item==temp);
            }
        }
    }

    public static void main(String[] args) {
        FiveSolution fiveSolution = new FiveSolution();
        System.out.println(fiveSolution.myPow(2,10));
        System.out.println(-(1l<<31));
        System.out.println(fiveSolution.generateParenthesis(1));
        System.out.println(fiveSolution.solveNQueens(4));
    }
}
