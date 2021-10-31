package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> seqList = new ArrayList<>();
        dfs(root,seqList);
        return String.join(",", seqList);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       cur = 0;
       seq = data.split(",");
       return store();
    }

    TreeNode store() {
        if(seq[cur].equals("null")) {
            cur++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(seq[cur]));
        cur++;
        root.left = store();
        root.right = store();
        return root;
    }

    public void dfs(TreeNode root, List<String> seqList) {
        if(root == null) {
            seqList.add("null");
            return;
        }
        seqList.add(String.valueOf(root.val));
        dfs(root.left, seqList);
        dfs(root.right,seqList);
    }
    private Integer cur;
    private String[] seq;
}
