package com.ixiaoyu2.rookie.class06;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class Symmetric {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isSymmetric(TreeNode root) {
        return symmetric(root, root);
    }

    public boolean symmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null ^ node2 == null) {
            return false;
        }

        return node1.val == node2.val && symmetric(node1.left, node2.right) && symmetric(node1.right, node2.left);
    }
}
