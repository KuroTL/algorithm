package com.ixiaoyu2.primary.class10;

import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/3/27 0027
 */
public class Code02_UnRecursiveTraversalBT {

    // 先序 头-左-右

    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            head = stack.pop();
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
            System.out.print(head.val + " ");
        }
    }

    // 中序 左 头 右

    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        pushLeft(stack, head);
        while (!stack.empty()) {
            head = stack.pop();
            System.out.print(head.val + " ");
            if (head.right != null) {
                pushLeft(stack, head.right);
            }
        }
    }

    private static void pushLeft(Stack<TreeNode> stack, TreeNode head) {
        stack.push(head);
        while (head.left != null) {
            stack.push(head.left);
            head = head.left;
        }
    }

    // 后序 左 右 头

    public static void pos1(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            // 头 右 左
            head = s1.pop();
            s2.push(head);
            if (head.left != null) {
                s1.push(head.left);
            }
            if (head.right != null) {
                s1.push(head.right);
            }
        }
        // 左 右 头
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }

        System.out.println();
    }

    public static void pos2(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        TreeNode cur = null;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && head != cur.left && head != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && head != cur.right) {
                stack.push(cur.right);
            } else {
                System.out.print(stack.pop().val + " ");
                head = cur;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
                 1
        *     2     3
        *   4  5  6   7
        *
        * */

        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.left.right.left = new TreeNode(8);
        head.left.right.right = new TreeNode(9);

        head.right = new TreeNode(3);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        pre(head);
        System.out.println("");
        in(head);
        System.out.println("");
        pos2(head);
    }

}
