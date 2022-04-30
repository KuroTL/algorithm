package com.ixiaoyu2.primary.class01;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/2
 * @Description :com.msb.primary.class01
 * @Version: 1.0
 */
public class Code04_BinarySearchExit {

    /**
     * 在一个有序数组中，找某个数是否存在 ，返回数的下标
     * @param arr 数组
     * @param num 数
     * @return 下标，不存在返回-1
     */
    public static int binarySearch(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    /*
     *以下为对数器用于此测试
     * */


    public static int comparator(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
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
            if ((binarySearch(arr, num) == -1) ^ (comparator(arr, num) == -1)) {
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
