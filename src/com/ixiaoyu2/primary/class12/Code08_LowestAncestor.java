package com.ixiaoyu2.primary.class12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author :Administrator
 * @date :2022/4/8 0008
 */
public class Code08_LowestAncestor {
    //给定一棵二叉树的头节点head，和另外两个节点a和b。
    //返回a和b的最低公共祖先


    public static TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return null;
        }

        return process(head, a, b).lowestAncestor;
    }

    private static class Info {
        boolean findA;
        boolean findB;
        TreeNode lowestAncestor;

        public Info(boolean findA, boolean findB, TreeNode lowestAncestor) {
            this.findA = findA;
            this.findB = findB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    private static Info process(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);

        boolean findA = leftInfo.findA || rightInfo.findA || node == a;
        boolean findB = leftInfo.findB || rightInfo.findB || node == b;
        TreeNode lowestAncestor = null;

        if (leftInfo.lowestAncestor != null) {
            lowestAncestor = leftInfo.lowestAncestor;
        } else if (rightInfo.lowestAncestor != null) {
            lowestAncestor = rightInfo.lowestAncestor;
        } else if (findA && findB) {
            lowestAncestor = node;
        }
        return new Info(findA, findB, lowestAncestor);
    }

    public static TreeNode lowestAncestor2(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return null;
        }
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        Set<TreeNode> set1 = new HashSet<>();
        TreeNode cur = a;
        set1.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            set1.add(cur);
        }
        cur = b;
        while (!set1.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(TreeNode head, Map<TreeNode, TreeNode> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
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
            TreeNode a = TestSample.pickRandomOne(node);
            TreeNode b = TestSample.pickRandomOne(node);
            ans1 = lowestAncestor(node, a, b);
            ans2 = lowestAncestor2(node, a, b);
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
