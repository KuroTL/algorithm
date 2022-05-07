package com.ixiaoyu2.primary.class18;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author :Administrator
 * @date :2022/5/7 0007
 */
public class Code01_SlidingWindowMaxArray {

    //假设一个固定大小为W的窗口，依次划过arr，
    //返回每一次滑出状况的最大值
    //例如，arr = [4,3,5,4,3,3,6,7], W = 3
    //返回：[5,5,5,4,6,7]

    public static int[] maxArray1(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        int n = arr.length;
        int[] ans = new int[n - w + 1];
        LinkedList<Integer> maxValueIndexDeque = new LinkedList<>();

        // ans数组下标
        int index = 0;
        // 窗口右边界
        int r = 0;
        while (r < n) {
            // 存放窗口内最大值索引的双端队列，将进入窗口的值放入队列（从大-》小排列）,小于等于新进入窗口的值从队列弹出
            while (!maxValueIndexDeque.isEmpty() && arr[maxValueIndexDeque.peekLast()] <= arr[r]) {
                maxValueIndexDeque.pollLast();
            }
            // 将进入窗口的数的索引加入队列
            maxValueIndexDeque.offerLast(r);
            // 达到窗口长度w时，在向右滑动，最左边的值滑出窗口
            if (maxValueIndexDeque.peekFirst() == r - w) {
                maxValueIndexDeque.poll();
            }
            // 窗口右边界大于等于窗口长度时，窗口构建成功
            if (r >= w - 1) {
                ans[index++] = arr[maxValueIndexDeque.peekFirst()];
            }
            r++;
        }
        return ans;
    }


    // 暴力方法
    public static int[] maxArray2(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        int n = arr.length;
        int[] ans = new int[n - w + 1];
        for (int l = 0; l <= n - w; l++) {
            int max = Integer.MIN_VALUE;
            for (int r = l; r - l < w; r++) {
                max = Math.max(max, arr[r]);
            }
            ans[l] = max;
        }
        return ans;
    }

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 10;
        int maxValue = 10;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = maxArray1(arr, w);
            int[] ans2 = right(arr, w);
            int[] ans3 = maxArray2(arr, w);
            if (!isEqual(ans2, ans3)) {
                System.out.println(w);
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(ans2));
                System.out.println(Arrays.toString(ans3));
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
