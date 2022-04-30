package com.ixiaoyu2.primary.class12;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/4/8 0008
 */
@SuppressWarnings("all")
public class Code07_MaxSubBSTHead {

    //给定一棵二叉树的头节点head，
    //返回这颗二叉树中最大的二叉搜索子树的头节点

    public static TreeNode maxSubSearchTreeHead(TreeNode root) {
        if (root == null) {
            return null;
        }
        return process(root).head;
    }

    private static class Info {
        int subSearchTreeSize;
        int max;
        int min;
        TreeNode head;

        public Info(int subSearchTreeSize, int max, int min, TreeNode head) {
            this.subSearchTreeSize = subSearchTreeSize;
            this.max = max;
            this.min = min;
            this.head = head;
        }
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int subSearchTreeSize = 0;
        int max = node.value;
        int min = node.value;
        TreeNode head = null;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            subSearchTreeSize = leftInfo.subSearchTreeSize;
            head = leftInfo.head;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            if (rightInfo.subSearchTreeSize > subSearchTreeSize) {
                subSearchTreeSize = rightInfo.subSearchTreeSize;
                head = rightInfo.head;
            }
        }
        // 左子树为空或者左子树为搜索树，且左子树最大值小于头结点值
        boolean leftBool = leftInfo == null || ((leftInfo.head == node.left) && (leftInfo.max < node.value));
        // 右子树为空或者右子树为搜索树，且右子树最小值大于头结点值
        boolean rightBool = rightInfo == null || ((rightInfo.head == node.right) && (rightInfo.min > node.value));
        // 左子树、右子树满足条件
        if (leftBool && rightBool) {
            head = node;
            subSearchTreeSize = (leftInfo == null ? 0 : leftInfo.subSearchTreeSize) + (rightInfo == null ? 0 : rightInfo.subSearchTreeSize) + 1;
        }
        return new Info(subSearchTreeSize, max, min, head);
    }


    public static TreeNode maxSubSearchTreeHead2(TreeNode head) {
        if (head == null) {
            return null;
        }
        int bstSize = getBSTSize(head);
        if (bstSize != 0) {
            return head;
        }
        TreeNode left = maxSubSearchTreeHead2(head.left);
        TreeNode right = maxSubSearchTreeHead2(head.right);
        return getBSTSize(left) >= getBSTSize(right) ? left : right;


    }

    private static int getBSTSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(node, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    private static void in(TreeNode node, ArrayList<TreeNode> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 3;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        TreeNode node = null;
        TreeNode ans1 = null;
        TreeNode ans2 = null;
        for (int i = 0; i < testTime; i++) {
            node = TestSample.generateRandomBT(maxLevel, maxValue);
            ans1 = maxSubSearchTreeHead(node);
            ans2 = maxSubSearchTreeHead2(node);
            if (ans1 != ans2) {
                System.out.println("出错啦");
                System.out.println(ans1);
                System.out.println(ans1.value);
                System.out.println(ans2);
                System.out.println(ans2.value);
                PrintBT.printTree(node);
                break;
            }
        }
    }
}
