package com.ixiaoyu2.primary.class19;

/**
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 *
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code05_SubArrayMinNumSum {

    //给定一个数组arr，返回所有子数组最小值的累加和

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n];
        int cursor = -1;
        long sum = 0L;

        for (int i = 0; i < n; i++) {
            while (cursor != -1 && arr[stack[cursor]] >= arr[i]) {
                int index = stack[cursor--];
                int left = cursor == -1 ? -1 : stack[cursor];
                int right = i;

                int num = (index - left) * (right - index);
                sum += (num * (long) arr[index]);
                sum %= 1000000007;
            }
            stack[++cursor] = i;
        }
        while (cursor != -1) {
            int index = stack[cursor--];
            int left = cursor == -1 ? -1 : stack[cursor];
            int right = n;
            int num = (index - left) * (right - index);
            sum += (num * (long) arr[index]);
            sum %= 1000000007;
        }
        return (int) sum;
    }


    public static int sumSubarrayMins2(int[] arr) {
        int[] stack = new int[arr.length];
        int[] left = nearLessEqualLeft(arr, stack);
        int[] right = nearLessRight(arr, stack);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long) arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public static int[] nearLessEqualLeft(int[] arr, int[] stack) {
        int N = arr.length;
        int[] left = new int[N];
        int size = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
                left[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            left[stack[--size]] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr, int[] stack) {
        int N = arr.length;
        int[] right = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && arr[stack[size - 1]] > arr[i]) {
                right[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            right[stack[--size]] = N;
        }
        return right;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 1000;
        int maxValue = 1000000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = sumSubarrayMins(arr);
            int ans2 = sumSubarrayMins2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
