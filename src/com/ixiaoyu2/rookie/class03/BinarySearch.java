package com.ixiaoyu2.rookie.class03;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/2/19
 * @Description :com.msb.rookie.class03
 * @Version: 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int testTimes = 10;
        int maxLength = 100;
        int maxValue = 500;
        int[] arr1 = getRandArray(maxLength, maxValue);
        int n;
        printArr(arr1);
        for (int i = 0; i < testTimes; i++) {
            n = getAreaLeast(arr1);
            if (n == 0) {
                if (arr1.length != 1 && arr1[0] > arr1[1]) {
                    printArr(arr1);
                    break;
                }
            } else if (n == arr1.length - 1) {
                if (arr1[arr1.length - 1] > arr1[arr1.length - 2]) {
                    printArr(arr1);
                    break;
                }
            } else if (arr1[n] > arr1[n - 1]) {
                printArr(arr1);
                break;
            }
        }
    }

    /**
     * 数组arr无序，但是相邻数不相等，获取一个局部最小值
     *
     * @param arr 数组
     * @return 局部最小值位置
     */
    public static int getAreaLeast(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }

        int l = 0;
        int r = arr.length - 1;
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[r] < arr[r - 1]) {
            return r;
        }

        // 只有arr.length()>2才会进入循环
        while (l < r - 1) {
            int mid = ((r - l) >> 1) + 1;
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            }
            if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            }
        }
        return arr[l] < arr[r] ? l : r;
    }

    /**
     * 在有序数组中找到num
     *
     * @param arr 要寻找的数数组
     * @param num 要寻找的数
     * @return int 数组下标
     */
    public static int traversing(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (num <= arr[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 在有序数组中找到num
     *
     * @param arr 要寻找的数数组
     * @param num 要寻找的数
     * @return int 数组下标
     */
    public static int searchByBinarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (arr[mid] == num) {
                return mid;
            }
            if (arr[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 有序数组中找到>=num最左的位置
     *
     * @param arr 数组
     * @param num 数
     * @return 查找的下标
     */
    public static int nearLeftIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (arr[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }


    /**
     * 获取随机长度随机元素的数组
     *
     * @param maxLength 数组最大长度
     * @param maxValue  数组元素最大值
     * @return 长度随机[0, maxLength)，值随机[0,maxValue)的数组
     */
    public static int[] getRandArray(int maxLength, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxLength)];
        arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }


    /**
     * 复制一个数组
     *
     * @param arr 数组
     * @return 复制得到的数组
     */
    public static int[] arrayCopy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] ans = new int[arr.length];
        System.arraycopy(arr, 0, ans, 0, arr.length);
        return ans;
    }

    /**
     * 打印数组元素
     *
     * @param arr 数组
     */
    public static void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.sort(arr);
    }
}
