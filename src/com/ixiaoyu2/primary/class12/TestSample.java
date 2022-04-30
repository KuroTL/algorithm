package com.ixiaoyu2.primary.class12;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class TestSample {

    public static TreeNode generateRandomBT(int maxLevel, int maxValue) {
        return generateProcess(1, maxLevel, maxValue);
    }

    private static TreeNode generateProcess(int level, int maxLevel, int maxValue) {
        // 如果层数大于最大层数返回空节点 或者40%概率为空节点
        if (level > maxLevel || Math.random() < 0.4) {
            return null;
        }
        TreeNode node = new TreeNode((int) (Math.random() * maxLevel));
        node.left = generateProcess(level + 1, maxLevel, maxValue);
        node.right = generateProcess(level + 1, maxLevel, maxValue);
        return node;
    }

    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPreList(arr, head);
        int i = (int) (Math.random() * arr.size());
        return arr.get(i);
    }

    private static void fillPreList(ArrayList<TreeNode> arr, TreeNode head) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPreList(arr, head.left);
        fillPreList(arr, head.right);
    }
}
