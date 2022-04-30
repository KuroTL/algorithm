package com.ixiaoyu2.primary.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author :Administrator
 * @Date :2022/3/12
 * @Description :com.msb.primary.class06
 * @Version: 1.0
 */
public class Code04_SortArrayDistanceLessK {
    
    //已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，
    // 并且k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。


    public static void sortArrLessDistanceK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(k - 1, arr.length - 1); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; index++, i++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    /*以下代码为对数器，用于此测试*/

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
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
        int maxLength = 10;
        int maxValue = 10;
        int testTimes = 1000000;
        int k = 4;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArrayNoMoveMoreK(maxLength, maxValue, k);
            int[] arr1 = copyArr(arr);
            int[] arr2 = copyArr(arr);
            comparator(arr1);
            sortArrLessDistanceK(arr2, k);
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
