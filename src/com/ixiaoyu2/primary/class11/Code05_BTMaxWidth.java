package com.ixiaoyu2.primary.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Code05_BTMaxWidth {
    //求二叉树最宽的层有多少个节点

    public static int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode curLevelEndNode = root;
        TreeNode nextLevelEndNode = null;
        int curLevelNodeNum = 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                nextLevelEndNode = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextLevelEndNode = cur.right;
            }
            curLevelNodeNum++;
            if (cur == curLevelEndNode) {
                max = Math.max(max, curLevelNodeNum);
                curLevelEndNode = nextLevelEndNode;
                curLevelNodeNum = 0;
            }
        }
        return max;
    }

    public static int maxWidthUseMap(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        HashMap<TreeNode, Integer> levelMap = new HashMap<>(16);
        levelMap.put(root, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                queue.offer(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode root = new TreeNode((int) (Math.random() * maxValue));
        root.left = generate(level + 1, maxLevel, maxValue);
        root.right = generate(level + 1, maxLevel, maxValue);
        return root;
    }

    public static void main(String[] args) {
        int maxLevel = 3;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            int ans1 = maxWidthUseMap(head);
            int ans2 = maxWidth(head);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                Code04_PrintBT.printTree(head);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");

    }

}
