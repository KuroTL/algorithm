package com.ixiaoyu2.primary.class19;

import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code01_MaxValueInAllSubArr {

    //  给定一个只包含正数的数组arr，arr中任何一个子数组sub，
    // 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，那么所有子数组中，这个值最大是多少？


    public static int maxValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = arr.length;
        int[] preSum = new int[n];
        preSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        return process(arr, preSum);
    }

    private static int process(int[] arr, int[] preSum) {
        int ans = 0;
        int[] stack = new int[arr.length];
        int cursor = -1;
        for (int i = 0; i < arr.length; i++) {
            while (cursor != -1 && arr[stack[cursor]] >= arr[i]) {
                int index = stack[cursor--];
                int left = cursor == -1 ? -1 : stack[cursor];
                int right = i;
                ans = left == -1 ? Math.max(ans, arr[index] * preSum[right - 1]) : Math.max(ans, arr[index] * (preSum[right - 1] - preSum[left]));
            }
            stack[++cursor] = i;
        }
        while (cursor != -1) {
            int index = stack[cursor--];
            int left = cursor == -1 ? -1 : stack[cursor];
            int right = arr.length;
            ans = left == -1 ? Math.max(ans, arr[index] * preSum[right - 1]) : Math.max(ans, arr[index] * (preSum[right - 1] - preSum[left]));
        }
        return ans;
    }


    public static int maxValue2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                    sum += arr[k];
                }
                ans = Math.max(ans, sum * min);
            }
        }
        return ans;
    }

    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (maxValue2(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
