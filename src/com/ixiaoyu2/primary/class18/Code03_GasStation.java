package com.ixiaoyu2.primary.class18;

import java.util.LinkedList;

/**
 * @author :Administrator
 * @date :2022/5/7 0007
 */
public class Code03_GasStation {

    // 加油站的良好出发点问题，
    //在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    //你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
    //给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，返回从每个加油站出发能否环绕一周，能够true，不能够false，返回从0~n开始出发能否环绕一周的数组。[treu,false,true……],表示从0开始可以环绕一周，1开始不能环绕一周~
    //链接：https://leetcode-cn.com/problems/gas-station

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] booleans = goodArray(gas, cost);
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] gas, int[] cost) {
        // n个加油站
        int n = gas.length;
        // 构建一个前缀和数组
        int m = n << 1;
        int[] arr = new int[m];
        //从i号出发，到i+1号加油站,邮箱剩余汽油数
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = gas[i] - cost[i];
        }
        // 0~n-1,表示从0出发回到0. 邮箱剩余汽油，n~2n-1,从0出发回到0邮箱剩余
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> minValueQueue = new LinkedList<>();

        // 构建长度为n的窗口，从0号加油站出发回到0号加油站
        for (int i = 0; i < n; i++) {
            while (!minValueQueue.isEmpty() && arr[minValueQueue.peekLast()] >= arr[i]) {
                minValueQueue.pollLast();
            }
            minValueQueue.offerLast(i);
        }

        boolean[] ans = new boolean[n];

        for (int offset = 0, i = 0, j = n; j < m; offset = arr[i++], j++) {

            if (arr[minValueQueue.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (minValueQueue.peekFirst() == i) {
                minValueQueue.poll();
            }

            // 从1号加油站回到1号加油站，……，n好加油站回到n号加油站的窗口
            while (!minValueQueue.isEmpty() && arr[minValueQueue.peekLast()] >= arr[j]) {
                minValueQueue.pollLast();
            }
            minValueQueue.offerLast(j);
        }
        return ans;
    }
}
