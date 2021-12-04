package leetcode;

public class EightSolution {
    //https://leetcode-cn.com/problems/merge-k-sorted-lists/
    public ListNode mergeKLists(ListNode[] lists) {
      return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
       if(root == null) {
           return new TreeNode(val);
       }
       if(val < root.val) {
           root.left = insertIntoBST(root.left, val);
       } else {
           root.right = insertIntoBST(root.right, val);
       }
       return root;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      TreeNode ans = null;
      TreeNode curr = root;
      while (curr != null) {
          if(curr.val == p.val) {
              if(curr.right != null) {
                  ans = curr.right;
                  while (ans.left != null) ans = ans.left;
              }
              break;
          }
          if(p.val < curr.val) {
              if(ans == null || ans.val > curr.val) ans = curr;
              curr = curr.left;
          } else {
              curr = curr.right;
          }
      }
      return ans;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
       if(root == null) {
           return null;
       }
       if(root.val == key) {
           if(root.left == null) return root.right;
           if(root.right == null) return root.left;
           TreeNode next = root.right;
           while (next.left != null) next = next.left;
           root.right =  deleteNode(root.right, next.val);
           root.val = next.val;
           return root;
       }
       if(key < root.val) {
           root.left = deleteNode(root.left, key);
       } else {
           root.right = deleteNode(root.right, key);
       }
       return root;
    }

    //https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
    public TreeNode convertBST(TreeNode root) {
        if(root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
    private int sum = 0;

    // https://leetcode-cn.com/problems/surrounded-regions/
    // TODO: 2021/12/4 被围绕的区域 
    public void solve(char[][] board) {

    }
}
