package com.ixiaoyu2.primary.class02;

import java.util.*;

/**
 * @Author :Administrator
 * @Date :2022/3/3
 * @Description :com.msb.primary.class02
 * @Version: 1.0
 */
public class Code05_KTimesMTimes {

    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     * M > 1,  K < M
     * 找到，出现了K次的数，
     * 要求，额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr
     * @return
     */
    public static Integer kTimesNum(int[] arr, int k, int m) {
        if (arr == null || arr.length < 3) {
            return null;
        }

        int length = 32;
        int[] bytes = new int[length];
        for (int i = 0; i < length; i++) {
            for (int num : arr) {
//                if ((num & (1 << i)) != 0) {
//                    bytes[i]++;
//                }
                bytes[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < length; i++) {
            if ((bytes[i] % m) == k) {
                ans |= (1 << i);
            }
        }
        return ans;
    }


    /*
     * 以下为对数器，用于测试
     * */

    public static Integer comparator(int[] arr, int k, int m) {
        if (arr == null || arr.length < 3) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == k) {
                return i;
            }
        }
        return null;
    }

    public static int[] generateKAndMArr(int k, int m, int numKinds, int maxValue) {

        int length = k + (numKinds - 1) * m;
        int[] ans = new int[length];
        int kNum = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);

        int i = 0;
        while (i < k) {
            ans[i++] = kNum;
        }
        for (int j = 0; j < numKinds - 1; j++) {
            int mNum = kNum + (int) (Math.random() * maxValue + 1);
            for (int i1 = i; i1 < i + m; i1++) {
                ans[i1] = mNum;
            }
            i = i + m;
        }
//
//        for (int j = 0; j < length; j++) {
//            int temp = ans[j];
//            int index = (int) (Math.random() * length);
//            ans[j] = ans[index];
//            ans[index] = temp;
//        }

        return ans;
    }


    public static void printArr(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxTimes = 10;
        int numKinds = 5;
        int maxValue = 10;
        int testTimes = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int k = (int) (Math.random() * maxTimes + 1);
            int m = k + (int) (Math.random() * (maxTimes - k) + 1);
            int[] arr = generateKAndMArr(k, m, numKinds, maxValue);
            Integer num1 = comparator(arr, k, m);
            Integer num2 = kTimesNum(arr, k, m);
            if (num1 == null ^ num2 == null) {
                System.out.println("算法出错1");
                Arrays.sort(arr);
                printArr(arr);
                System.out.println(num1);
                System.out.println(num2);
                break;
            }
            if (num1 != null && !num1.equals(num2)) {
                System.out.println("算法出错2");
                Arrays.sort(arr);
                printArr(arr);
                System.out.println(num1);
                System.out.println(num2);
                break;
            }
            if (num2 != null && !num2.equals(num1)) {
                System.out.println("算法出错2");
                printArr(arr);
                System.out.println(num1);
                System.out.println(num2);
                break;
            }
        }
        System.out.println("测试结束~");
    }

}
