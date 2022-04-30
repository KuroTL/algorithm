package com.ixiaoyu2.primary.code17;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/4/23 0023
 */
public class Code03_Knapsack {

    //给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值。
    //给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。
    //返回你能装下最多的价值是多少?


    public static int mostValuable(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || weights.length != values.length || bag < 0) {
            return 0;
        }

        return process(weights, values, 0, bag);
    }

    /**
     * 返回最大价值
     *
     * @param weights 物品种量
     * @param values  物品价值
     * @param i       当前物品位置
     * @param bag     背包剩余数量
     * @return 获得的最大价值
     */
    private static int process(int[] weights, int[] values, int i, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (i == weights.length) {
            return 0;
        }
        // 没有选当前位置的物品
        int p1 = process(weights, values, i + 1, bag);
        // 选了当前的物品
        int p2 = 0;
        if (bag - weights[i] >= 0) {
            p2 = values[i] + process(weights, values, i + 1, bag - weights[i]);
        }
        return Math.max(p1, p2);
    }

    // 优化1
    /*
     *process(weights,values,0,bag) -> process(weights,values,1,bag),process(weights,values,1,bag-weights[0])
     *
     *  process(weights,values,1,bag) -> process(weights,values,2,bag),process(weights,values,2,bag-weights[1])
     *  process(weights,values,1,bag-weights[0]) ->  process(weights,values,2,bag-weight[0]),process(weights,values,1,bag-weights[0]-weight[1])
     *   如果： bag-weights[1] = bag-weight[0]，那么process(weights,values,2,bag-weights[1])与 process(weights,values,2,bag-weight[0]) 重复
     *
     *  加入缓存表进行优化
     * */

    public static int mostValuable2(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || weights.length != values.length || bag < 0) {
            return 0;
        }
        int n = weights.length;
        int[][] map = new int[n + 1][bag + 1];
        for (int[] arr : map) {
            Arrays.fill(arr, -1);
        }
        return process2(weights, values, 0, bag, map);
    }

    /**
     * 返回最大价值
     *
     * @param weights 物品种量
     * @param values  物品价值
     * @param i       当前物品位置
     * @param bag     背包剩余数量
     * @return 获得的最大价值
     */
    private static int process2(int[] weights, int[] values, int i, int bag, int[][] map) {
        if (bag < 0) {
            return -1;
        } else if (i == weights.length) {
            return 0;
        } else if (map[i][bag] != -1) {
            return map[i][bag];
        } else {
            int ans = 0;
            // 没有选当前位置的物品
            int p1 = process2(weights, values, i + 1, bag, map);
            // 选了当前的物品
            int p2 = 0;
            if (bag - weights[i] >= 0) {
                p2 = values[i] + process2(weights, values, i + 1, bag - weights[i], map);
            }
            ans = Math.max(p1, p2);
            map[i][bag] = ans;
            return ans;
        }
    }


    // 优化3 转化为动态规划表

    public static int mostValuable3(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || weights.length != values.length || bag < 0) {
            return 0;
        }
        int n = weights.length;
        int[][] map = new int[n + 1][bag + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int res = 0; res <= bag; res++) {
                int p1 = map[i + 1][res];
                int p2 = 0;
                if (res - weights[i] >= 0) {
                    p2 = values[i] + map[i + 1][res - weights[i]];
                }
                map[i][res] = Math.max(p1, p2);
            }
        }
        return map[0][bag];
    }


    // -----------------------以下为对数器--------------------------------------

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process0(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process0(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    private static int[] generateArr(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength + 1);
        int[] ans = new int[length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1);
        }
        return ans;
    }

    private static int[] gengerateSameLengthArr(int[] arr, int maxValue) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1);
        }
        return ans;
    }

    public static void main(String[] args) {

/*        int[] weights = {3, 2, 5, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 6, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(mostValuable(weights, values, bag));
        System.out.println(maxValue(weights, values, bag));
        System.out.println(mostValuable2(weights, values, bag));
        System.out.println(mostValuable3(weights, values, bag));*/

        int maxLength = 20;
        int maxValue = 40;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] weights = generateArr(maxLength, maxValue);
            int[] values = gengerateSameLengthArr(weights, maxValue);
            int bag = (int) (Math.random() * maxValue + 1) * 2;
            int ans0 = maxValue(weights, values, bag);
            int ans1 = mostValuable(weights, values, bag);
            int ans2 = mostValuable2(weights, values, bag);
            int ans3 = mostValuable3(weights, values, bag);
            if (ans0 != ans1 || ans0 != ans2 || ans0 != ans3) {
                System.out.println("出错啦！！！！");
                System.out.println("weights: " + Arrays.toString(weights));
                System.out.println("values: " + Arrays.toString(values));
                System.out.println("bag: " + bag);
                System.out.println("ans0: " + ans0);
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                System.out.println("ans3: " + ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
