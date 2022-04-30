package com.ixiaoyu2.primary.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Code03_EncodeNaryTreeToBinaryTree {


    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = encode(root.children);
        return head;
    }

    private TreeNode encode(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode treeNode = new TreeNode(child.val);
            if (head == null) {
                head = treeNode;
            } else {
                cur.right = treeNode;
            }
            cur = treeNode;
            cur.left = encode(child.children);
        }
        return head;
    }


    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    private List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }

}
