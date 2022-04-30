package com.ixiaoyu2.rookie.class07;

/**
 * @Author :Administrator
 * @Date :2022/2/25
 * @Description :com.msb.rookie.class07
 * @Version: 1.0
 */
public class PathSum {
    //给定二叉树的根和一个整数targetSum，如果树有根到叶的路径，使得沿着路径的所有值相加等于targetSum，则返回true。

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        pathSum(root, 0, targetSum);
        return isSum;
    }

    public void pathSum(TreeNode root, int preSum, int targetSum) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == targetSum) {
                isSum = true;
            }
            return;
        }
        preSum += root.val;
        if (root.left != null) {
            pathSum(root.left, preSum, targetSum);
        }
        if (root.right != null) {
            pathSum(root.right, preSum, targetSum);
        }
    }

}
