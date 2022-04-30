package com.ixiaoyu2.rookie.class06;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class TraversalBinaryTree {

   static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void traversalBinaryTree(Node node){
        if (node==null){
            return;
        }

        traversalBinaryTree(node.left);

        traversalBinaryTree(node.right);
        System.out.print(node.val+ " ");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        traversalBinaryTree(head);
    }
}
