package com.ixiaoyu2.primary.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Code02_SerializeAndReconstructBT {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     * */

    /*
     * 通过先序遍历序列化
     * */

    public static Queue<String> preSerialize(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue;
    }

    private static void pres(TreeNode treeNode, Queue<String> queue) {
        if (treeNode == null) {
            queue.offer(null);
        } else {
            queue.add(String.valueOf(treeNode.val));
            pres(treeNode.left, queue);
            pres(treeNode.right, queue);
        }
    }

    public static TreeNode buildByPreSerialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preBuild(queue);
    }

    private static TreeNode preBuild(Queue<String> queue) {
        String val = queue.poll();
        if (val == null) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = preBuild(queue);
        root.right = preBuild(queue);
        return root;
    }

    /*
     * 通过后序遍历序列化
     * */

    public static Queue<String> posSerialize(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        poss(root, queue);
        return queue;
    }

    private static void poss(TreeNode treeNode, Queue<String> queue) {
        if (treeNode == null) {
            queue.offer(null);
        } else {
            poss(treeNode.left, queue);
            poss(treeNode.right, queue);
            queue.offer(String.valueOf(treeNode.val));
        }
    }

    public static TreeNode buildByPosSerialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posBuild(stack);
    }

    private static TreeNode posBuild(Stack<String> stack) {
        String val = stack.pop();
        if (val == null) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.right = posBuild(stack);
        root.left = posBuild(stack);
        return root;
    }

    /*
     * 通过按层遍历序列化
     * */

    public static Queue<String> levelSerialize(TreeNode root) {
        Queue<String> queue = new LinkedList<>();

        Queue<TreeNode> help = new LinkedList<>();
        if (root == null) {
            queue.offer(null);
        } else {
            queue.offer(String.valueOf(root.val));
            help.offer(root);
            while (!help.isEmpty()) {
                TreeNode cur = help.poll();
                if (cur.left == null) {
                    queue.offer(null);
                } else {
                    queue.offer(String.valueOf(cur.left.val));
                    help.offer(cur.left);
                }
                if (cur.right == null) {
                    queue.offer(null);
                } else {
                    queue.offer(String.valueOf(cur.right.val));
                    help.offer(cur.right);
                }
            }
        }
        return queue;
    }

    public static TreeNode buildByLevelSerialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }

        String val = queue.poll();
        TreeNode root = generateTreeNode(val);
        Queue<TreeNode> help = new LinkedList<>();
        if (root != null) {
            help.offer(root);
        }

        while (!help.isEmpty()) {
            TreeNode cur = help.poll();
            cur.left = generateTreeNode(queue.poll());
            cur.right = generateTreeNode(queue.poll());
            if (cur.left != null) {
                help.offer(cur.left);
            }
            if (cur.right != null) {
                help.offer(cur.right);
            }
        }
        return root;
    }

    private static TreeNode generateTreeNode(String s) {
        if (s == null) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(s));
        }
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

    public static boolean isSameValueStructure(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null ^ root2 != null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSameValueStructure(root1.left, root2.left) && isSameValueStructure(root1.right, root2.right);
    }

    // for test
    public static void printTree(TreeNode root) {
        System.out.println("Binary Tree:");
        printInOrder(root, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode root, int height, String to, int len) {
        if (root == null) {
            return;
        }
        printInOrder(root.right, height + 1, "v", len);
        String val = to + root.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(root.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerialize(head);
            Queue<String> pos = posSerialize(head);
            Queue<String> level = levelSerialize(head);
            TreeNode preBuild = buildByPreSerialize(pre);
            TreeNode posBuild = buildByPosSerialize(pos);
            TreeNode levelBuild = buildByLevelSerialize(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        TreeNode head = generateRandomBST(maxLevel, maxValue);
        printTree(head);
        Queue<String> pre = preSerialize(head);
        TreeNode preBuild = buildByPreSerialize(pre);
        printTree(preBuild);

        System.out.println("test finish!");

    }
}
