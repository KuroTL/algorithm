package com.ixiaoyu2.primary.class13;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/4/9 0009
 */
public class Code03_LeadtCostSplitGod {

    /*一块金条切成两半，是需要花费和长度数值一样的铜板的。
    比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?

    例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。

    如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
    但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。

    输入一个数组，返回分割的最小代价。
    */

    public static int leastCostSplitGod2(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        return process(arr, 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价

    private static int process(int[] arr, int pre) {
        if (arr == null || arr.length <= 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }


    public static int leastCostSplitGod(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : arr) {
            priorityQueue.add(num);
        }
        int ans = 0;

        while (priorityQueue.size() > 1) {
            Integer num1 = priorityQueue.poll();
            Integer num2 = priorityQueue.poll();
            ans = ans + num1 + num2;
            priorityQueue.add(num1 + num2);
        }
        return ans;
    }


    public static int[] randomArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1);
        }
        return ans;
    }

    public static int[] arrayCopy(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        System.arraycopy(arr, 0, ans, 0, arr.length);
        return ans;
    }

    public static void main(String[] args) {
        int maxLength = 4;
        int maxValue = 10;
        int testTime = 1000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = randomArr(maxLength, maxValue);
            int[] arr2 = arrayCopy(arr1);
            int ans1 = leastCostSplitGod(arr1);
            int ans2 = leastCostSplitGod2(arr2);
            if (ans1 != ans2) {
                System.out.println("出错~");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(Arrays.toString(arr1));
                break;
            }
        }

        System.out.println("测试结束");
    }
}
