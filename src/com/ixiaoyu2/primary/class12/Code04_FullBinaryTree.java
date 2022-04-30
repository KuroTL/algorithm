package com.ixiaoyu2.primary.class12;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class Code04_FullBinaryTree {

    // 是否为满二叉树
    // 第1种方法
    // 收集子树是否是满二叉树
    // 收集子树的高度
    // 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的

    public static boolean isFullTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return precess(root).full;
    }

    private static class Info {
        int height;
        boolean full;

        public Info(int height, boolean full) {
            this.height = height;
            this.full = full;
        }
    }

    private static Info precess(TreeNode node) {
        if (node == null) {
            return new Info(0, true);
        }
        Info leftInfo = precess(node.left);
        Info rightInfo = precess(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean full = leftInfo.full && rightInfo.full && leftInfo.height == rightInfo.height;
        return new Info(height, full);
    }

    public static boolean isFullTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info2 info2 = process2(root);
        return info2.nodes == (1 << info2.height) - 1;

    }

    // 第2种方法
    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n

    private static class Info2 {
        int height;
        int nodes;

        public Info2(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private static Info2 process2(TreeNode node) {
        if (node == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process2(node.left);
        Info2 rightInfo = process2(node.right);
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info2(height, nodes);
    }

    public static void main(String[] args) {

        int maxLevel = 10;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TestSample.generateRandomBT(maxLevel, maxValue);
            boolean ans1 = isFullTree(node);
            boolean ans2 = isFullTree2(node);
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
