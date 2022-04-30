package com.ixiaoyu2.rookie.class07;

/**
 * @Author :Administrator
 * @Date :2022/2/25
 * @Description :com.msb.rookie.class07
 * @Version: 1.0
 */
public class IsBalancedTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;

        int height = Math.max(rightInfo.height, leftInfo.height) + 1;
        return new Info(isBalanced, height);
    }

    private class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int hight) {
            this.isBalanced = isBalanced;
            this.height = hight;
        }

        public Info() {
        }
    }


}
