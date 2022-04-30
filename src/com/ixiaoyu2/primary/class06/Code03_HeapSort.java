package com.ixiaoyu2.primary.class06;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/12
 * @Description :com.msb.primary.class06
 * @Version: 1.0
 */
public class Code03_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        //将数组调整为大根堆
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }


        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        while ((2 * index + 1) < heapSize) {
            int large = ((2 * index + 2) < heapSize) && (arr[2 * index + 2] > arr[2 * index + 1]) ? (2 * index + 2) : (2 * index + 1);
            large = arr[large] > arr[index] ? large : index;
            if (large == index) {
                break;
            }
            swap(arr, large, index);
            index = large;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*以下代码为对数器，用于此测试*/
    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] generateArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxLength) - (int) (Math.random() * maxLength);
        }
        return ans;
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        System.arraycopy(arr, 0, ans, 0, arr.length);
        return ans;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
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
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 10;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(maxLength, maxValue);
            int[] arr1 = copyArr(arr);
            int[] arr2 = copyArr(arr);
            comparator(arr1);
            heapSort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错啦~");
                printArr(arr);
                printArr(arr1);
                printArr(arr2);
                break;
            }

        }
        System.out.println("测试结束");
    }
}
