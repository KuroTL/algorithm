package com.ixiaoyu2.primary.class05;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/8
 * @Description :com.msb.primary.class05
 * @Version: 1.0
 */
public class Code02_NetherlandFlag {

    //给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
    //要求额外空间复杂度O(1)，时间复杂度O(N)

    public static void netherlandFlag(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int lessR = -1;
        int moreL = arr.length - 1;
        int i = 0;
        while (i <= moreL) {
            if (arr[i] < num) {
                swap(arr, i++, ++lessR);
            }else if (arr[i] == num) {
                i++;
            }else {
                swap(arr, i, moreL--);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2,  8, 6, 0,7, 2, 3, 5, 0};
        netherlandFlag(arr, 7);
        System.out.println(Arrays.toString(arr));
    }

}
