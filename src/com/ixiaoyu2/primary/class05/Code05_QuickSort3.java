package com.ixiaoyu2.primary.class05;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/8
 * @Description :com.msb.primary.class05
 * @Version: 1.0
 */
public class Code05_QuickSort3 {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] partition = partition(arr, left, right);
        process(arr, left, partition[0] - 1);
        process(arr, partition[1] + 1, right);
    }

    public static int[] partition(int[] arr, int left, int right) {
        int lessR = left - 1;
        swap(arr,right,(int) (Math.random()*(right-left+1))+left);
        int moreL = right;
        int i = left;
        while (i < moreL) {
            if (arr[i] < arr[right]) {
                swap(arr, i++, ++lessR);
            } else if (arr[i] > arr[right]) {
                swap(arr, i, --moreL);
            } else {
                i++;
            }
        }
        swap(arr, right, moreL);
        return new int[]{lessR + 1, moreL};

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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
            quickSort(arr1);
            comparator(arr2);
            if (!arrIsEqual(arr1,arr2)){
                System.out.println("算法出错");
                printArr(arr);
                printArr(arr1);
                printArr(arr2);
                break;
            }

        }
        System.out.println("测试结束~");
    }
}
