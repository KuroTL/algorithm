package com.ixiaoyu2.primary.class19;

/**
 * Count Submatrices With All Ones
 * <p>
 * https://leetcode.com/problems/count-submatrices-with-all-ones/submissions/
 *
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code04_RectangleNum {

    //给定一个二维数组matrix，其中的值不是0就是1，
    //返回全部由1组成的子矩形数量

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat.length == 0) {
            return 0;
        }

        int m = mat[0].length;
        int[] heights = new int[m];
        int num = 0;
        for (int[] rows : mat) {
            for (int i = 0; i < m; i++) {
                heights[i] = rows[i] == 0 ? 0 : heights[i] + 1;
            }
            num += rectNum(heights);
        }
        return num;
    }

    private static int rectNum(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] stack = new int[n];
        int num = 0;
        int cursor = -1;
        for (int i = 0; i < n; i++) {
            while (cursor != -1 && height[stack[cursor]] >= height[i]) {
                int index = stack[cursor--];

                int left = cursor == -1 ? -1 : stack[cursor];
                int right = i;
                int maxMinIndex = left == -1 ? right : height[left] > height[right] ? left : right;

                num += (height[index] - height[maxMinIndex]) * num(right - left - 1);

            }
            stack[++cursor] = i;
        }
        while (cursor != -1) {
            int index = stack[cursor--];
            int left = cursor == -1 ? -1 : stack[cursor];
            int right = n;
            int maxMinIndex = left == -1 ? -1 : left;
            int down = maxMinIndex == -1 ? 0 : height[left];
            num += (height[index] - down) * num(right - left - 1);
        }
        return num;
    }

    private static int num(int n) {
        return (n * (n + 1)) >> 1;
    }
}
