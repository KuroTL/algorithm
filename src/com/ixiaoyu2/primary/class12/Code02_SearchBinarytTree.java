package com.ixiaoyu2.primary.class12;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class Code02_SearchBinarytTree {

    //判断二叉树是否是搜索二叉树
    // 搜索二叉树，左子树最大值小于头结点，右子树最小值大于头结点的值

    public static boolean isSearchBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).searchTree;
    }

    private static class Info {
        boolean searchTree;
        int maxValue;
        int minValue;

        public Info(boolean searchTree, int maxValue, int minValue) {
            this.searchTree = searchTree;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxValue = node.value;
        int minValue = node.value;

        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
        }
        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
        }
        boolean searchTree = false;
        if (leftInfo != null && rightInfo != null) {
            searchTree = leftInfo.searchTree && rightInfo.searchTree && (leftInfo.maxValue < node.value) && (rightInfo.minValue > node.value);
        } else if (rightInfo != null) {
            searchTree = rightInfo.searchTree && (rightInfo.minValue > node.value);
        } else if (leftInfo != null) {
            searchTree = leftInfo.searchTree && (leftInfo.maxValue < node.value);
        } else {
            searchTree = true;
        }
        return new Info(searchTree, maxValue, minValue);
    }

    public static boolean isSearchBT2(TreeNode root) {
        if (root == null) {
            return true;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(root, arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).value >= arr.get(i + 1).value) {
                return false;
            }
        }
        return true;
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

        int maxLevel = 10;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TestSample.generateRandomBT(maxLevel, maxValue);
            boolean ans1 = isSearchBT(node);
            boolean ans2 = isSearchBT2(node);
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
