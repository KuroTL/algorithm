package com.ixiaoyu2.primary.class19;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 单调栈结构
 * 一种特别设计的栈结构，为了解决如下的问题：
 * <p>
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
 *
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[i] < arr[stack.peek()]) {
                int index = stack.pop();
                ans[index][0] = stack.empty() ? -1 : stack.peek();
                ans[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            int index = stack.pop();
            ans[index][0] = stack.empty() ? -1 : stack.peek();
            ans[index][1] = -1;
        }
        return ans;
    }


    public static int[][] getNearLess(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[i] < arr[stack.peek().peekLast()]) {
                LinkedList<Integer> list = stack.pop();
                int leftIndex = stack.empty() ? -1 : stack.peek().peekLast();
                for (int index : list) {
                    ans[index][0] = leftIndex;
                    ans[index][1] = i;
                }
            }
            if (!stack.empty() && arr[i] == arr[stack.peek().peekLast()]) {
                stack.peek().offerLast(i);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.offerLast(i);
                stack.push(list);
            }
        }
        while (!stack.empty()) {
            LinkedList<Integer> list = stack.pop();
            int leftIndex = stack.empty() ? -1 : stack.peek().peekLast();
            for (int index : list) {
                ans[index][0] = leftIndex;
                ans[index][1] = -1;
            }
        }
        return ans;
    }


    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
