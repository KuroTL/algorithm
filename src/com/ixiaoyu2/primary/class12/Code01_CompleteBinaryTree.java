package com.ixiaoyu2.primary.class12;

import java.util.LinkedList;

/**
 * @author :Administrator
 * @date :2022/4/7 0007
 */
public class Code01_CompleteBinaryTree {

    // 判断二叉树是否是完全二叉树
    // 完全二叉树：树的每一层都是满树，就算不满，也是最后一层不满，也是从左往右变满的趋势

    public static boolean completeBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 二叉树按层遍历
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean hasNoChild = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode leftChild = node.left;
            TreeNode rightChild = node.right;

            // 遇到hasNoChild的节点后，后面的节点必须是叶节点（没有2个孩子）
            if (hasNoChild && (leftChild != null || rightChild != null)) {
                return false;
            }
            // 没有左孩子，有有孩子，直接返回false
            if (rightChild != null && leftChild == null) {
                return false;
            }
            if (leftChild != null) {
                queue.offer(leftChild);
            }
            if (rightChild != null) {
                queue.offer(rightChild);
            }
            // 第一次遇到没有孩子的节点后,将hasNoChild 设为true
            if (leftChild == null || rightChild == null) {
                hasNoChild = true;
            }
        }
        return true;
    }

    public static boolean completeBT2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).completeBT;
    }

    private static class Info {
        boolean fullTree;
        boolean completeBT;
        int heigth;

        public Info(boolean fullTree, boolean completeBT, int heigth) {
            this.fullTree = fullTree;
            this.completeBT = completeBT;
            this.heigth = heigth;
        }
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        //1 以node为根节点的二叉树是否为满二叉树：左子树为满二叉树，右子树为满二叉树，同时左子树与右子树一样高
        boolean fullTree = leftInfo.fullTree && rightInfo.fullTree && leftInfo.heigth == rightInfo.heigth;
        //2 以node为根节点的二叉树的高度，左子树和右子树最大高度+1
        int height = Math.max(leftInfo.heigth, rightInfo.heigth) + 1;
        //3 以node为根节点的二叉树是否为完全二叉树，先设为false
        boolean completeBT = false;
        //3.1 如果以node为根节点的二叉树是满二叉树，那么一定是完全二叉树
        if (fullTree) {
            completeBT = true;
        } else {
            boolean differ = (leftInfo.heigth - rightInfo.heigth == 1);
            //3.2  如果以node为根节点的二叉树不是满二叉树,但是做子树和右子树是满二叉树
            if (leftInfo.fullTree && rightInfo.fullTree) {
                // 左子树、右子树都是满树，那么只有左子树比右子树多1层，以node为根节点的二叉树才是完全二叉树
                if (differ) {
                    completeBT = true;
                }
            } else if (!leftInfo.fullTree && rightInfo.fullTree) {
                // 左子树不是满树，右子树是满树，那么必须左子树是完全二叉树，左子树比右子树多1层
                if (leftInfo.completeBT && differ) {
                    completeBT = true;
                }
            } else if (leftInfo.fullTree) {
                // 左子树是满树，右子树不是满树，那么必须右子树必须是完全二叉树，左子树和右子树一样高
                if (rightInfo.completeBT && (leftInfo.heigth == rightInfo.heigth)) {
                    completeBT = true;
                }
            }
        }
        return new Info(fullTree, completeBT, height);
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 10;
        int testTime = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TestSample.generateRandomBT(maxLevel, maxValue);
            boolean ans1 = completeBT(node);
            boolean ans2 = completeBT2(node);
            if (ans1 != ans2) {
                System.out.println("出错啦");
                System.out.println(ans1);
                System.out.println(ans2);
                PrintBT.printTree(node);
                break;
            }
        }
        System.out.println("测试结束！");
    }

}
