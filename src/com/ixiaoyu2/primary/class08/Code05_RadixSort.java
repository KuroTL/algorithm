package com.ixiaoyu2.primary.class08;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/3/20 0020
 */
public class Code05_RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(max, j);
        }
        int digitMaxBit = 0;
        while (max != 0) {
            digitMaxBit++;
            max /= 10;
        }
        radixSort(arr, 0, arr.length - 1, digitMaxBit);
    }


    public static void radixSort(int[] arr, int left, int right, int digitMaxBit) {
        final int radix = 10;
        int[] help = new int[right - left + 1];
        // 0-9位

        for (int d = 1; d <= digitMaxBit; d++) {
            int[] count = new int[radix];
            for (int j = left; j <= right; j++) {
                int k = getDigit(arr[j], d);
                count[k]++;
            }
            // 转换为前缀和数组
            for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (int i = right; i >= left; i--) {
                int k = getDigit(arr[i], d);
                help[count[k] - 1] = arr[i];
                count[k]--;
            }
            for (int i = left, j = 0; i <= right; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        // 获取每位上的值0~9
        return (x / (int) Math.pow(10, d - 1)) % 10;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 5;
        int maxValue = 100000;
        boolean succeed = true;
        int[] arr1 = null;
        int[] arr2 = null;

        for (int i = 0; i < testTime; i++) {
            arr1 = generateRandomArray(maxSize, maxValue);
            arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
