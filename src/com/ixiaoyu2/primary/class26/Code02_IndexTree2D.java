package com.ixiaoyu2.primary.class26;

/**
 * IndexTree用于二维数组
 *
 * @author :Administrator
 * @date :2022/5/27 0027
 */
public class Code02_IndexTree2D {

    public static class IndexTree2D {
        private int[][] tree;
        private int[][] nums;
        private int r;
        private int c;

        public IndexTree2D(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            r = matrix.length;
            c = matrix[0].length;
            tree = new int[r + 1][c + 1];
            nums = new int[r][c];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, nums[i], 0, c);
                System.arraycopy(matrix[i], 0, tree[i + 1], 1, c);
            }
        }

        public int sum(int row, int col) {
            int res = 0;
            for (int i = row + 1; i > 0; i -= (i & -i)) {
                for (int j = col + 1; j > 0; j -= (j & -j)) {
                    res += tree[i][j];
                }
            }
            return res;
        }

        public void update(int row, int col, int d) {
            if (r == 0 || c == 0) {
                return;
            }

            int add = d - nums[row][col];
            nums[row][col] = d;
            for (int i = row + 1; i <= r; i += (i & -i)) {
                for (int j = col + 1; j <= c; j += (j & -j)) {
                    tree[i][j] += add;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (r == 0 || c == 0) {
                return 0;
            }
            return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
        }
    }
}
