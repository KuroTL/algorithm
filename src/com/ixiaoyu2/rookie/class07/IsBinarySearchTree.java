package com.ixiaoyu2.rookie.class07;

/**
 * @Author :Administrator
 * @Date :2022/2/25
 * @Description :com.msb.rookie.class07
 * @Version: 1.0
 */
public class IsBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

//    public static boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        traversal(root,list);
//        boolean isBinarySearchTree = true;
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (list.get(i) > list.get(i + 1)) {
//                isBinarySearchTree = false;
//                break;
//            }
//        }
//        return isBinarySearchTree;
//    }
//
//    private static void traversal(TreeNode root, ArrayList<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        traversal(root.left, list);
//        list.add(root.val);
//        System.out.println(root.val);
//        traversal(root.right, list);
//    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        Info info = new Info(true, root.val, root.val);
        if (leftInfo != null) {
            info.min = Math.min(leftInfo.min, info.min);
            info.max = Math.max(leftInfo.max, info.max);
        }
        if (rightInfo != null) {
            info.min = Math.min(rightInfo.min, info.min);
            info.max = Math.max(rightInfo.max, info.max);
        }
        if (leftInfo != null && !leftInfo.isBST) {
            info.isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            info.isBST = false;
        }
        boolean leftMaxLessRoot = leftInfo == null ? true : (leftInfo.max < root.val);
        boolean lrightMinMoreRoot = rightInfo == null ? true : (rightInfo.min > root.val);
        if (!leftMaxLessRoot || !lrightMinMoreRoot) {
            info.isBST = false;
        }
        return info;
    }


    private class Info {
        boolean isBST;
        int max;
        int min;

        public Info() {
        }

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }


}
