package com.ixiaoyu2.primary.class21;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author :Administrator
 * @date :2022/5/14 0014
 */
public class Code01_SubTreeSameWithStructure {

    // 给定两棵二叉树的头节点head1和head2，想知道head1中是否有某个子树的结构和head2完全一样

    static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

    }

    public static boolean containsSameSubTree(TreeNode head1, TreeNode head2) {
        if (head2 == null) {
            return true;
        }
        if (head1 == null) {
            return false;
        }

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list1 = preProcess(head1, list1);
        list2 = preProcess(head2, list2);
        return getIndex(list1, list2) != -1;
    }


    private static ArrayList<String> preProcess(TreeNode head, ArrayList<String> list) {
        if (head == null) {
            list.add(null);
            return list;
        }
        list.add(String.valueOf(head.value));
        preProcess(head.left, list);
        preProcess(head.right, list);
        return list;
    }

    private static int getIndex(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1 == null || list2 == null || list1.size() < 1 || list1.size() < list2.size()) {
            return -1;
        }

        int n = list1.size(), m = list2.size();

        int[] next = getNextArray(list2);

        int i1 = 0;
        int i2 = 0;

        while (i1 < n && i2 < m) {
            if (Objects.equals(list1.get(i1), list2.get(i2))) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == m ? i1 - i2 : -1;
    }

    private static int[] getNextArray(ArrayList<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        int n = list.size();
        if (n == 1) {
            return new int[]{-1};
        }
        int[] next = new int[n];

        next[0] = -1;
        next[1] = 0;

        int pos = 2;
        int cn = 0;

        while (pos < n) {
            if (Objects.equals(list.get(pos - 1), list.get(cn))) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }


    // 对数器

    public static boolean containsTree1(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 120;
        int smallTreeLevel = 35;
        int nodeMaxValue = 50;
        int testTimes = 100000;
        System.out.println("test begin");
        TreeNode bigBack = null;
        TreeNode smallBack = null;
        boolean ans1 = false;
        boolean ans2 = false;
        for (int i = 0; i < testTimes; i++) {
            TreeNode big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            TreeNode small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            ans1 = containsTree1(big, small);
            ans2 = containsSameSubTree(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                bigBack = big;
                smallBack = small;
                break;
            }
        }

        System.out.println("test finish!");
    }
}


