package com.wyt.wytlab.leetcode.tree;

import java.util.*;

public class TreeTest {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2, new TreeNode(1, null, null), null);
        TreeNode root = new TreeNode(-2, null, right);

//        List<Integer> list = new ArrayList<>();
//        inorderTraversal(root, list);
//        List<List<Integer>> list = levelOrder(root);
//        System.out.println(list);

//        TreeNode right = new TreeNode(-3, null, null);
//        TreeNode root = new TreeNode(-2, null, right);
        Solution solution = new Solution();
//        System.out.println(solution.isSymmetric(root));
        System.out.println(solution.hasPathSum(root, -2));
        System.out.println(new Random().nextInt());

//        Codec codec = new Codec();
//        String s = codec.serialize(root);
//        TreeNode treeNode = codec.deserialize(s);

        TreeNode treeNode = solution.searchBST(root, 2);
    }

    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preOrder(root, list);
        return list;
    }

    private static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.getVal());
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<Integer>();
        if (root.left != null) {
            list.addAll(postorderTraversal(root.left));
        }
        if (root.right != null) {
            list.addAll(postorderTraversal(root.right));
        }
        list.add(root.val);
        return list;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            List<Integer> elist = new ArrayList<>();
            Integer size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                elist.add(treeNode.val);
                if(treeNode.left != null) {
                    queue.add(treeNode.left);
                }

                if(treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            result.add(elist);
        }
        return result;
    }

}
