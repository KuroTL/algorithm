package com.ixiaoyu2.primary.class24;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/5/17 0017
 */
public class Code01_BinarySearchTree {

    // 利用Morris遍历判断是否为搜索二叉树
    // 搜索二叉树：搜索二叉树每一个节点值都不相同，且根节点的值大于左子树的最大值，小于右子树最小值

    private static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }


    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        boolean ans = true;
        Integer pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value) {
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }

    public static boolean isSearchBT2(Node root) {
        if (root == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(root, arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).value >= arr.get(i + 1).value) {
                return false;
            }
        }
        return true;
    }

    private static void in(Node node, ArrayList<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }


    static class TestSample {

        public static Node generateRandomBT(int maxLevel, int maxValue) {
            return generateProcess(1, maxLevel, maxValue);
        }

        private static Node generateProcess(int level, int maxLevel, int maxValue) {
            // 如果层数大于最大层数返回空节点 或者40%概率为空节点
            if (level > maxLevel || Math.random() < 0.4) {
                return null;
            }
            Node node = new Node((int) (Math.random() * maxLevel));
            node.left = generateProcess(level + 1, maxLevel, maxValue);
            node.right = generateProcess(level + 1, maxLevel, maxValue);
            return node;
        }

        public static Node pickRandomOne(Node head) {
            if (head == null) {
                return null;
            }
            ArrayList<Node> arr = new ArrayList<>();
            fillPreList(arr, head);
            int i = (int) (Math.random() * arr.size());
            return arr.get(i);
        }

        private static void fillPreList(ArrayList<Node> arr, Node head) {
            if (head == null) {
                return;
            }
            arr.add(head);
            fillPreList(arr, head.left);
            fillPreList(arr, head.right);
        }
    }


    public static void main(String[] args) {

        int maxLevel = 10;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTime; i++) {
            Node node = TestSample.generateRandomBT(maxLevel, maxValue);
            boolean ans1 = isSearchBT2(node);
            boolean ans2 = isBST(node);
            if (ans1 != ans2) {
                System.out.println("出错啦");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束！");
    }

}
