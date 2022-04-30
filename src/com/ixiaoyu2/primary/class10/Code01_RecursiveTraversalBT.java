package com.ixiaoyu2.primary.class10;

/**
 * @author :Administrator
 * @date :2022/3/27 0027
 */
public class Code01_RecursiveTraversalBT {

    public static void f(TreeNode head) {
        if (head == null) {
            return;
        }
        f(head.left);
        f(head.right);
    }
    // 先序打印 头-左-右

    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre(head.left);
        pre(head.right);
    }

    // 中序打印 左-头-右

    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.val + " ");
        in(head.right);
    }

    // 后序打印 左-右-头

    public static void pos(TreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val + " ");
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
        head.right = new TreeNode(3);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        pre(head);
        System.out.println("");
        in(head);
        System.out.println("");
        pos(head);
        System.out.println("");
    }


}
