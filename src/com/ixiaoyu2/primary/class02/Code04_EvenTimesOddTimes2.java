package com.ixiaoyu2.primary.class02;

import java.util.*;

/**
 * @Author :Administrator
 * @Date :2022/3/3
 * @Description :com.msb.primary.class02
 * @Version: 1.0
 */
public class Code04_EvenTimesOddTimes2 {

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     *
     * @param arr
     */
    public static List<Integer> evenTimesOddTimes2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        //假设2个奇数次的数为a和b，eor =a^b
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        //a!=b,则eor>0,eor1为eor二进制最右侧的1
        int eor1 = eor & (~eor + 1);
        int ans1 = 0;
        for (int i : arr) {
            if ((i & eor1) == 0) {
                ans1 = ans1 ^ i;
            }
        }
        List<Integer> ans = new ArrayList<>(2);
        ans.add(ans1);
        ans.add(ans1 ^ eor);
        return ans;
    }


    /*
     * 以下为对数器，用于测试
     * */


    public static List<Integer> comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        List<Integer> ans = new ArrayList<>(2);
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
                ans.add(key);
            }
        }
        return ans;
    }

    public static int[] generateEvenAndOddArr(int maxTimes, int numKinds, int maxValue) {

        //2个数出现的奇数次
        int times = (int) (Math.random() * maxTimes + 1);
        int oddTimes = (times & 1) == 0 ? times + 1 : times;

        int times2 = (int) (Math.random() * maxTimes + 1);
        int oddTimes2 = (times2 & 1) == 0 ? times2 + 1 : times2;

        //剩下的偶数次数的种数
        int[] evenTimes = new int[numKinds - 2];

        //生成数组的长度
        int length = oddTimes + oddTimes2;

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
        int oddNum2 = oddNum + (int) (Math.random() * maxValue + 1);
        // 先把生成的出现奇数次的数放入ans数组

        int i = 0;
        while (i < oddTimes) {
            ans[i++] = oddNum;
        }
        while (i < oddTimes + oddTimes2) {
            ans[i++] = oddNum2;
        }

        //把出现偶数次的数放入ans数组
        for (int i1 = 0; i1 < evenTimes.length; i1++) {
            int evenNum = (int) (Math.random() * maxValue + 1) + (int) (Math.random() * maxValue + 1) + oddNum - oddNum2;
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

    public static boolean check(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 != null ^ list2 != null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int num : list1) {
            if (!list2.contains(num)) {
                return false;
            }
        }
        for (int num : list2) {
            if (!list1.contains(num)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int maxTimes = 5;
        int numKinds = 20;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateEvenAndOddArr(maxTimes, numKinds, maxValue);
            List<Integer> list1 = comparator(arr);
            List<Integer> list2 = evenTimesOddTimes2(arr);
            if (!check(list1, list2)) {
                System.out.println("算法出错了~");
                Arrays.sort(arr);
                System.out.println(Arrays.toString(arr));
                for (Integer integer : list1) {
                    System.out.print(integer + " ");
                }
                System.out.println();
                for (Integer integer : list2) {
                    System.out.print(integer + " ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println("测试结束~");
    }
}
