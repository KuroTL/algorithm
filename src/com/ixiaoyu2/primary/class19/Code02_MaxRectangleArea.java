package com.ixiaoyu2.primary.class19;

/**
 * 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 *
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code02_MaxRectangleArea {

    // 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int[] stack = new int[n];
        int cursor = -1;
        int area = 0;
        for (int i = 0; i < n; i++) {
            while (cursor != -1 && heights[stack[cursor]] >= heights[i]) {
                int index = stack[cursor--];
                int left = cursor == -1 ? -1 : stack[cursor];
                int right = i;
                area = left == -1 ? Math.max(area, heights[index] * right) : Math.max(area, heights[index] * (right - 1 - left));
            }
            stack[++cursor] = i;
        }
        while (cursor != -1) {
            int index = stack[cursor--];
            int left = cursor == -1 ? -1 : stack[cursor];
            int right = n;
            area = left == -1 ? Math.max(area, heights[index] * right) : Math.max(area, heights[index] * (right - 1 - left));
        }
        return area;
    }


}
