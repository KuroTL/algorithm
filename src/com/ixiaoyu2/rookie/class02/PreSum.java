package com.ixiaoyu2.rookie.class02;

/**
 * @Author :Administrator
 * @Date :2022/2/17
 * @Description :com.msb.rookie.class02
 * @Version: 1.0
 */
public class PreSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(getRangeSum1(arr, 4, 7));
        System.out.println(getRangeSum2(arr, 4, 7));
        System.out.println(getRangeSum3(arr, 4, 7));
    }

    /**
     * 求数组 给定区间的和
     *
     * @param arr        数组
     * @param leftIndex  区间左边下标
     * @param rightIndex 区间右边下标
     * @return 给定区间的和
     */
    public static int getRangeSum1(int[] arr, int leftIndex, int rightIndex) {
        int sum = 0;
        int[] preSum = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            preSum[i] = sum + arr[i];
            sum = preSum[i];
        }
        return leftIndex == 0 ? preSum[rightIndex] : preSum[rightIndex] - preSum[leftIndex - 1];
    }

    /**
     * 求数组 给定区间的和
     *
     * @param arr        数组
     * @param leftIndex  区间左边下标
     * @param rightIndex 区间右边下标
     * @return 给定区间的和
     */
    public static int getRangeSum2(int[] arr, int leftIndex, int rightIndex) {
        int sum = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
            sum += arr[i];
        }
        return sum;
    }

    /**
     * 求数组 给定区间的和
     *
     * @param arr        数组
     * @param leftIndex  区间左边下标
     * @param rightIndex 区间右边下标
     * @return 给定区间的和
     */
    public static int getRangeSum3(int[] arr, int leftIndex, int rightIndex) {
        int[][] table = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            table[i] = new int[arr.length - i];
            for (int j = 0, k = i; j < arr.length - i && k < arr.length; j++, k++) {
                table[i][j] = sum + arr[k];
                sum = table[i][j];
            }
        }
        return table[leftIndex][rightIndex - leftIndex];
    }
}
