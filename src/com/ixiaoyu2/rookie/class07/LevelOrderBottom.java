package com.ixiaoyu2.rookie.class07;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author :Administrator
 * @Date :2022/2/25
 * @Description :com.msb.rookie.class07
 * @Version: 1.0
 */
public class LevelOrderBottom {

    //给定二叉树的根，返回其节点值的自底向上顺序遍历。(即从左到右，从叶到根，一层一层)。

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


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> curAns = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            ans.add(0, curAns);
        }
        return ans;
    }


}
