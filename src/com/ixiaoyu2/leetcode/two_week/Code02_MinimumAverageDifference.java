package com.ixiaoyu2.leetcode.two_week;

import java.util.PriorityQueue;

/**
 * 最小平均差
 * https://leetcode.cn/problems/minimum-average-difference/
 *
 * @author :Administrator
 * @date :2022/4/30 0030
 */
public class Code02_MinimumAverageDifference {

    //给你一个下标从 0 开始长度为 n 的整数数组 nums 。
    //下标 i 处的 平均差 指的是 nums 中 前 i + 1 个元素平均值和 后 n - i - 1 个元素平均值的 绝对差 。两个平均值都需要 向下取整 到最近的整数。
    //请你返回产生 最小平均差 的下标。如果有多个下标最小平均差相等，请你返回 最小 的一个下标。
    //注意：
    //两个数的 绝对差 是两者差的绝对值。
    // n 个元素的平均值是 n 个元素之 和 除以（整数除法） n 。
    //0 个元素的平均值视为 0 。

    public static int minimumAverageDifference(int[] nums) {
        int length = nums.length;
        long[] preSum = new long[length];
        preSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            preSum[i] += preSum[i - 1] + nums[i];
        }

        PriorityQueue<Info> priorityQueue = new PriorityQueue<>((info1, info2) -> info1.p - info2.p == 0 ? info1.index - info2.index : Long.compare(info1.p, info2.p));

        Info info = null;
        for (int i = 0; i < length - 1; i++) {
            priorityQueue.add(new Info(i, Math.abs(preSum[i] / (i + 1) - (preSum[length - 1] - preSum[i]) / (length - i - 1))));
        }
        priorityQueue.add(new Info(length - 1, preSum[length - 1] / length));
        return priorityQueue.poll().index;
    }

    private static class Info {
        int index;
        long p;

        public Info(int index, long p) {
            this.index = index;
            this.p = p;
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 3, 9, 5, 3};
        int i = minimumAverageDifference(a);
        System.out.println(i);
    }
}
