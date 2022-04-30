package com.ixiaoyu2.primary.class14;

/**
 * @author :Administrator
 * @date :2022/4/13 0013
 */
public class Code02_FindCrcle {


    // 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
    //
    //返回矩阵中 省份 的数量。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/number-of-provinces

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length;
        UnionSet unionSet = new UnionSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionSet.union(i, j);
                }
            }
        }
        return unionSet.sets();
    }


    private static class UnionSet {
        /**
         * 当前节点的表示主线节点
         */
        private int[] parents;
        /**
         * 表示祖先节点当前大小
         */
        private int[] size;
        /**
         * 辅助数组
         */
        private int[] help;
        /**
         * 当前并查集集合集合个数
         */
        private int setSize;

        public UnionSet(int n) {
            parents = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
            setSize = n;
        }

        /**
         * 找到该节点的根节点
         *
         * @param i 下标为i的及诶到哪
         * @return 根节点的下标，如果给定的下标越界，返回-1，表示不存在
         */
        private int findRoot(int i) {
            // 如果给定的下标越界，返回-1，表示不存在
            if (i < 0 || i >= parents.length) {
                return -1;
            }
            int index = 0;
            while (parents[i] != i) {
                help[index++] = i;
                i = parents[i];
            }
            for (index--; index >= 0; index--) {
                parents[help[index]] = i;
            }
            return i;
        }

        /**
         * 将下标为i,j的两个节点所在的集合合并
         *
         * @param i 下标i所在节点
         * @param j 下标j所在节点
         */
        public void union(int i, int j) {
            // 如果有一个下标越界，直接返回
            if (i < 0 || i >= parents.length || j < 0 || j >= parents.length) {
                return;
            }
            int rootI = findRoot(i);
            int rootJ = findRoot(j);
            if (rootI != rootJ) {
                int sizeI = size[rootI];
                int sizeJ = size[rootJ];
                if (sizeI > sizeJ) {
                    parents[rootJ] = rootI;
                    size[rootI] = sizeI + sizeJ;
                } else {
                    parents[rootI] = rootJ;
                    size[rootJ] = sizeI + sizeJ;
                }
                setSize--;
            }
        }

        /**
         * 返回并查集集合个数
         *
         * @return 集合个数
         */
        public int sets() {
            return setSize;
        }
    }
}
