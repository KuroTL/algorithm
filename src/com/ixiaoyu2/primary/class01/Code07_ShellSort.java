package com.ixiaoyu2.primary.class01;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/3/21 0021
 */
public class Code07_ShellSort {

    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int h = 1;
        while (h <= arr.length / 3) {
            h = 3 * h + 1;
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1 && arr[j] < arr[j - gap]; j -= gap) {
                    swap(arr, j, j - gap);
                }
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }/*
     * 以下代码为对数器，用于测试
     * */

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * (maxLength + 1)) - (int) (Math.random() * (maxLength + 1));
        }
        return ans;
    }

    public static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        System.arraycopy(arr, 0, ans, 0, arr.length);
        return ans;
    }

    public static void printArr(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static boolean arrIsEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
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


    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 200;
        int testTimes = 1000000;
        int[] arr1 = generateRandomArr(maxLength, maxValue);
        int[] arr2 = copyArr(arr1);

        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            shellSort(arr1);
            comparator(arr2);
            if (!arrIsEqual(arr1, arr2)) {
                System.out.println("挑战失败，算法出错");
                printArr(arr1);
                System.out.println();
                printArr(arr2);
                break;
            }
        }
        System.out.println("测试结束~");
    }
}
