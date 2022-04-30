package com.ixiaoyu2.rookie.class06;

import java.util.HashMap;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class BuilderTree {
    //    给定两个整数数组preorder和inorder，
//    其中preorder是二叉树的前序遍历，
//    inorder是同一棵树的前序遍历，构造并返回二叉树

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }


        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1,hashMap);

    }

    public TreeNode buildTree(int[] preorder, int s, int e, int[] inorder, int s1, int e1, HashMap<Integer, Integer> hashMap) {

        if (s > e) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[s]);
        if (s == e) {
            return root;
        }
        int i = hashMap.get(preorder[s]);

        root.left = buildTree(preorder, s + 1, s + i - s1, inorder, s1, i - 1,hashMap);
        root.right = buildTree(preorder, s + i - s1 + 1, e, inorder, i + 1, e1,hashMap);
        return root;
    }
}
