package com.ixiaoyu2.primary.class01;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/2
 * @Description :com.msb.primary.class01
 * @Version: 1.0
 */
public class Code05_BinarySearchNearLeft {

    /**
     * 在一个有序数组中，找>=某个数最左侧的位置
     *
     * @param arr
     * @param num
     * @return
     */
    public static int binarySearchNearLeft(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int index = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= num) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }


    /*
     *以下为对数器用于此测试
     */

    public static int comparator(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int[] generateRandomSortedArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void printArr(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 20;
        int testTimes = 100000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomSortedArr(maxLength, maxValue);
            int num = (int) Math.random() * (maxValue + 1);
            if (binarySearchNearLeft(arr, num) != comparator(arr, num)) {
                System.out.println("挑战失败，算法出错");
                printArr(arr);
                System.out.println();
                System.out.println(num);
                break;
            }
        }
        System.out.println("测试结束~");
    }

}
