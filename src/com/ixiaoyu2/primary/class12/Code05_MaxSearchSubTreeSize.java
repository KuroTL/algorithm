package com.ixiaoyu2.primary.class12;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class Code05_MaxSearchSubTreeSize {
    // 给定一棵二叉树的头节点head，
    //返回这颗二叉树中最大的二叉搜索子树的大小

    public static int maxSBTSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return precess(root).maxSBTSize;
    }

    private static class Info {
        int maxSBTSize;
        int size;
        int max;
        int min;

        public Info(int maxSBTSize, int size, int max, int min) {
            this.maxSBTSize = maxSBTSize;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

    private static Info precess(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = precess(node.left);
        Info rightInfo = precess(node.right);

        int size = 1;
        int max = node.value;
        int min = node.value;

        if (leftInfo != null) {
            size += leftInfo.size;
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            size += rightInfo.size;
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        int p1 = leftInfo != null ? leftInfo.maxSBTSize : 0;
        int p2 = rightInfo != null ? rightInfo.maxSBTSize : 0;
        int p3 = 0;
        boolean leftIsSearchTree = leftInfo == null || leftInfo.maxSBTSize == leftInfo.size;
        boolean rightSearchTree = rightInfo == null || rightInfo.maxSBTSize == rightInfo.size;

        if (leftIsSearchTree && rightSearchTree) {
            boolean leftMaxLessNode = leftInfo == null || leftInfo.max < node.value;
            boolean rightMinMoreNode = rightInfo == null || rightInfo.min > node.value;
            if (leftMaxLessNode && rightMinMoreNode) {
                int leftSize = leftInfo == null ? 0 : leftInfo.size;
                int rightSize = rightInfo == null ? 0 : rightInfo.size;
                p3 = leftSize + rightSize + 1;
            }
        }
        int maxSBTSize = Math.max(p1, Math.max(p2, p3));
        return new Info(maxSBTSize, size, max, min);
    }


    public static int maxSBTSize2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int bstSize = getBSTSize(root);
        if (bstSize != 0) {
            return bstSize;
        }
        return Math.max(maxSBTSize2(root.left), maxSBTSize2(root.right));
    }

    private static int getBSTSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(root, arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).value >= arr.get(i + 1).value) {
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
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TestSample.generateRandomBT(maxLevel, maxValue);
            int ans1 = maxSBTSize(node);
            int ans2 = maxSBTSize2(node);
            if (ans1 != ans2) {
                System.out.println("出错啦");
                System.out.println(ans1);
                System.out.println(ans2);
                PrintBT.printTree(node);
                break;
            }
        }
        System.out.println("测试结束！");
    }
}
