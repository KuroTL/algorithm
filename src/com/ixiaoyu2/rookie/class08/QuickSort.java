package com.ixiaoyu2.rookie.class08;

import java.util.Arrays;

/**
 * @Author :Administrator
 * @Date :2022/3/1
 * @Description :com.msb.rookie.class08
 * @Version: 1.0
 */
public class QuickSort {
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
        swap(arr,left+(int) (Math.random()*(right-left+1)),right);
        int[] equalE = partition(arr, left, right);
        process(arr, left, equalE[0] - 1);
        process(arr, equalE[1] + 1, right);
    }


    public static int[] partition(int[] arr, int left, int right) {
        int lessR = left - 1;
        int moreL = right;
        int i = left;
        while (i < moreL) {
            if (arr[i] < arr[right]) {
                swap(arr, ++lessR, i++);
            } else if (arr[i] > arr[right]) {
                swap(arr, i, --moreL);
            } else {
                i++;
            }
        }
        swap(arr, moreL, right);
        return new int[]{lessR + 1, moreL};
    }
//
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


//
//    public static int[] partition(int[] arr, int L, int R) {
//        int lessR = L - 1;
//        int moreL = R;
//        int index = L;
//        while (index < moreL) {
//            if (arr[index] < arr[R]) {
//                swap(arr, ++lessR, index++);
//            } else if (arr[index] > arr[R]) {
//                swap(arr, --moreL, index);
//            } else {
//                index++;
//            }
//        }
//        swap(arr, moreL, R);
//        return new int[] { lessR + 1, moreL };
//    }
//
//    public static void quickSort1(int[] arr) {
//        if (arr == null || arr.length < 2) {
//            return;
//        }
//        process(arr, 0, arr.length - 1);
//    }
//
//    public static void process(int[] arr, int L, int R) {
//        if (L >= R) {
//            return;
//        }
//        int[] equalE = partition(arr, L, R);
//        process(arr, L, equalE[0] - 1);
//        process(arr, equalE[1] + 1, R);
//    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
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

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//		int[] arr = { 7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4 };
//
//		splitNum2(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}

        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//           int[] arr1 = {9,5,4,3,2,6,5,4};
//            printArray(arr1);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Oops!");
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
