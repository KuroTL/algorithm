package com.ixiaoyu2.rookie.class06;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class SameTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null ^ q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }
}
