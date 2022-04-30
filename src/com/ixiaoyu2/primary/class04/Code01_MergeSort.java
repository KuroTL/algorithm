package com.ixiaoyu2.primary.class04;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/7
 * @Description :com.msb.primary.class04
 * @Version: 1.0
 */
public class Code01_MergeSort {


    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;

        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }


    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        int length = arr.length;
        while (mergeSize < length) {
            int left = 0;
            while (left < length) {
                if (mergeSize > length - left) {
                    break;
                }
                int mid = left + mergeSize - 1;
                int right = mid + Math.min(mergeSize, length - mid - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
            if (mergeSize > (left >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
    }




    /*以下为对数器，用于测试*/

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        if (ans.length < 1) {
            return ans;
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxLength + 1) - (int) (Math.random() * maxLength + 1);
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

    public static boolean arrIsEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 != null ^ arr2 != null) {
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

    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int maxLength = 30;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            int[] arr1 = copyArr(arr);
            int[] arr2 = copyArr(arr);
            int[] arr3 = copyArr(arr);
//            printArr(arr);
//            printArr(arr1);
//            printArr(arr2);
            comparator(arr1);
            mergeSort(arr2);
            mergeSort2(arr3);
            if (!arrIsEqual(arr1, arr2) || !arrIsEqual(arr1, arr3)) {
                System.out.println("算法出错啦~");
                printArr(arr);
                printArr(arr1);
                printArr(arr2);
                printArr(arr3);
                break;
            }
        }
        System.out.println("测试结束~");
    }
}
