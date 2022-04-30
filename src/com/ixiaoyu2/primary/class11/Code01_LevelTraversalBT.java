package com.ixiaoyu2.primary.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Code01_LevelTraversalBT {

    public static void levelTravesalBT(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(3);
        head.right = new TreeNode(2);
        head.left.left = new TreeNode(6);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(4);
        head.right.right = new TreeNode(7);
        head.left.right.left = new TreeNode(8);

        levelTravesalBT(head);
    }
}
