package com.ixiaoyu2.rookie.class01;

/**
 * @Author :Administrator
 * @Date :2022/2/16
 * @Description :com.msb.rookie.class01
 * @Version: 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {9,17,3,2,5,6,1,2,0,6,4,3,8,9,2};
        printArr(arr);
        insertSort(arr);
        System.out.println();
        printArr(arr);
    }

    /**
     * 插入排序
     * @param arr 无序数组
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 交换数组两个位置的数
     *
     * @param arr 数组
     * @param i   下标
     * @param j   下标
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组内容
     *
     * @param arr 数组
     */
    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
