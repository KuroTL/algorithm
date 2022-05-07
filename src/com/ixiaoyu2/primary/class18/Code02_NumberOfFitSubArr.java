package com.ixiaoyu2.primary.class18;

import java.util.LinkedList;

/**
 * @author :Administrator
 * @date :2022/5/7 0007
 */
public class Code02_NumberOfFitSubArr {
    //给定一个整型数组arr，和一个整数num，某个arr中的子数组sub，如果想达标，必须满足：
    //sub中最大值 – sub中最小值 <= num，返回arr中达标子数组的数量

    public static int numberOfAubArr1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        // 存放最大值索引的双端队列
        LinkedList<Integer> maxWindow = new LinkedList<>();
        // 存放最小值索引的双端队列
        LinkedList<Integer> minWindow = new LinkedList<>();
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n) {
                // 最大值索引 从左往右
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[r]) {
                    maxWindow.pollLast();
                }
                maxWindow.offerLast(r);
                // 最小值索引
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[r]) {
                    minWindow.pollLast();
                }
                minWindow.offerLast(r);

                // 满足要求的窗口内的所有子数组都满足要求
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > num) {
                    break;
                } else {
                    r++;
                }
            }
            // 在l~r范围，以l开头的子数组
            ans += r - l;

            // 如果此时l到窗口最大值，移除当前最大值，重新构建窗口
            if (maxWindow.peekFirst() == l) {
                maxWindow.pollFirst();
            }
            // 如果此时l到窗口最小值，移除当前最小值，重新构建窗口
            if (minWindow.peekFirst() == l) {
                minWindow.pollFirst();
            }
        }
        return ans;
    }

    // 暴力方法
    public static int numberOfAubArr2(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;


        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = l; i <= r; i++) {
                    min = Math.min(min, arr[i]);
                    max = Math.max(max, arr[i]);
                }
                if (max - min <= num) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000;
        System.out.println("测试开始");
        int[] arr = null;
        for (int i = 0; i < testTime; i++) {
            arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = numberOfAubArr1(arr, sum);
            int ans2 = numberOfAubArr2(arr, sum);
            //  int ans3 = numberOfAubArr2(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                // System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
