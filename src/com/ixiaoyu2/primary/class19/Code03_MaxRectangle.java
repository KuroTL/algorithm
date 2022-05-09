package com.ixiaoyu2.primary.class19;

/**
 * Maximal Rectangle
 * https://leetcode.com/problems/maximal-rectangle/submissions/
 *
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code03_MaxRectangle {


    //给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形，内部有多少个1

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix[0].length;
        int[] height = new int[m];
        int area = 0;
        for (char[] row : matrix) {
            for (int i = 0; i < m; i++) {
                height[i] = row[i] == '0' ? 0 : height[i] + 1;
            }
            area = Math.max(area, largestRectangleArea(height));
        }
        return area;
    }

    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int cursor = -1;
        int area = 0;
        for (int i = 0; i < n; i++) {
            while (cursor != -1 && heights[stack[cursor]] >= heights[i]) {
                int index = stack[cursor--];
                int left = cursor == -1 ? -1 : stack[cursor];
                int right = i;
                area = Math.max(area, heights[index] * (right - 1 - left));
            }
            stack[++cursor] = i;
        }
        while (cursor != -1) {
            int index = stack[cursor--];
            int left = cursor == -1 ? -1 : stack[cursor];
            int right = n;
            area = Math.max(area, heights[index] * (right - 1 - left));
        }
        return area;
    }
}
