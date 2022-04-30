package com.ixiaoyu2.primary.class02;

import java.util.*;

/**
 * @Author :Administrator
 * @Date :2022/3/3
 * @Description :com.msb.primary.class02
 * @Version: 1.0
 */
public class Code02_EvenTimeOddTimes {
    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     *
     * @param arr
     * @return
     */
    public static Integer findOddTimesNum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }


    /*
     * 以下为对数器，用于测试
     * */

    public static Integer comparator(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            if ((map.get(key) & 1) == 1) {
                return key;
            }
        }
        return null;
    }


    public static int[] generateEvenAndOddArr(int maxTimes, int numKinds, int maxValue) {

        int times = (int) (Math.random() * maxTimes + 1);
        //1个数出现的奇数次
        int oddTimes = (times & 1) == 0 ? times - 1 : times;
        //剩下的偶数次数的种数
        int[] evenTimes = new int[numKinds - 1];

        //生成数组的长度
        int length = oddTimes;

        //出现偶数次的每种数,出现的偶数次
        for (int i = 0; i < evenTimes.length; i++) {
            do {
                evenTimes[i] = (int) (Math.random() * maxTimes + 1);
            } while ((evenTimes[i] & 1) == 1);
            length += evenTimes[i];
        }
        int[] ans = new int[length];
        //出现奇数次的数
        int oddNum = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        // 先把生成的出现奇数次的数放入ans数组

        int i = 0;
        while (i < oddTimes) {
            ans[i++] = oddNum;
        }

        //把出现偶数次的数放入ans数组
        for (int i1 = 0; i1 < evenTimes.length; i1++) {
            int evenNum = oddNum + (int) (Math.random() * maxValue + 1);
            for (int j = i; j < i + evenTimes[i1]; j++) {
                ans[j] = evenNum;
            }
            i = i + evenTimes[i1];
        }
        //打乱顺序
        for (int j = 0; j < length; j++) {
            int temp = ans[j];
            int index = (int) (Math.random() * length);
            ans[j] = ans[index];
            ans[index] = temp;
        }
        return ans;
    }

    public static void prinArr(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        int maxTimes = 10; //每种数出现的最大次数
        int numKinds = 10; //出现数字的种数
        int maxValue = 10;//测试数组中最大的数
        int testTimes = 100000;//测试次数
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateEvenAndOddArr(maxTimes, numKinds, maxValue);
            Integer num1 = findOddTimesNum(arr);
            Integer num2 = comparator(arr);
            if ((num1 == null ^ num2 == null)) {
                System.out.println("算法出错1~");
                prinArr(arr);
                System.out.println(num1);
                System.out.println(num2);
                break;
            }

            if (num1 != null && num2 != null && !num1.equals(num2)) {
                System.out.println("算法出错2~");
                prinArr(arr);
                System.out.println();
                System.out.println(num1);
                System.out.println(num2);
                break;
            }
        }

        System.out.println("测试结束~");

    }
}