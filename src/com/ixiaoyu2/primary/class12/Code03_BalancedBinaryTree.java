package com.ixiaoyu2.primary.class12;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class Code03_BalancedBinaryTree {
    //是否为平衡二叉树
    // 平衡二叉树，任一节点对应的两棵子树的最大高度差为1，因此它也被称为平衡二叉树

    public static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).balancedTree;
    }

    private static class Info {
        boolean balancedTree;
        int height;

        public Info(boolean balancedTree, int height) {
            this.balancedTree = balancedTree;
            this.height = height;
        }
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean balancedTree = leftInfo.balancedTree && rightInfo.balancedTree && (Math.abs(leftInfo.height - rightInfo.height) < 2);
        return new Info(balancedTree, height);
    }


    public static boolean isBalancedTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean[] ans = {true};
        process2(root, ans);
        return ans[0];
    }

    private static int process2(TreeNode node, boolean[] ans) {
        if (!ans[0] || node == null) {
            return -1;
        }
        int leftHeight = process2(node.left, ans);
        int rightHeight = process2(node.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TestSample.generateRandomBT(maxLevel, maxValue);
            boolean ans1 = isBalancedTree(node);
            boolean ans2 = isBalancedTree2(node);
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
