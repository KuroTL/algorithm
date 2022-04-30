package com.ixiaoyu2.primary.class05;

/**
 * @Author :Administrator
 * @Date :2022/3/8
 * @Description :com.msb.primary.class05
 * @Version: 1.0
 */
public class Code01_Partition {
    //给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
    //要求额外空间复杂度O(1)，时间复杂度O(N)

    public static void partition(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int lessR = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                swap(arr, ++lessR, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        int maxLength = 20;
        int maxSize = 10;
        int[] arr = generateRandomArr(maxLength, maxSize);
        printArr(arr);
        partition(arr, 0);
        printArr(arr);
    }
}
