package com.ixiaoyu2.rookie.class07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Administrator
 * @Date :2022/2/25
 * @Description :com.msb.rookie.class07
 * @Version: 1.0
 */
public class PathSum2 {
    //给定二叉树的根和一个整数targetSum，返回所有根到叶的路径，其中路径中节点值的和等于targetSum。
    // 每个路径应该作为节点值的列表而不是节点引用返回。
    //根到叶路径是从根开始到任何叶节点结束的路径。叶节点是没有子节点的节点。

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> subList = new ArrayList<>();
        process(root, 0, targetSum, subList, ans);
        return ans;
    }

    public void process(TreeNode root, int preSum, int targetSum, List<Integer> subList, List<List<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == targetSum) {
                subList.add(root.val);
                ans.add(copyList(subList));
                subList.remove(subList.size() - 1);
            }
            return;
        }
        subList.add(root.val);
        preSum += root.val;
        if (root.left != null) {
            process(root.left, preSum, targetSum, subList, ans);
        }
        if (root.right != null) {
            process(root.right, preSum, targetSum, subList, ans);
        }

        subList.remove(subList.size() - 1);
    }

    private List<Integer> copyList(List<Integer> list) {
        if (list == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ans.add(list.get(i));
        }
        return ans;
    }


}
