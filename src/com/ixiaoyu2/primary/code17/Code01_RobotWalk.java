package com.ixiaoyu2.primary.code17;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/4/20 0020
 */
public class Code01_RobotWalk {


    //假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
    //开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
    //如果机器人来到1位置，那么下一步只能往右来到2位置；
    //如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
    //如果机器人来到中间位置，那么下一步可以往左走或者往右走；
    //规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
    //给定四个参数 N、M、K、P，返回方法数。


    // 暴力递归方式

    /**
     * @param n 一排有n个位置
     * @param m 开始时机器人在m位置
     * @param k 机器人必须走k步
     * @param p 最终需要到的位置
     * @return 方法数
     */
    public static int robotWalkWays(int n, int m, int k, int p) {
        if (m < 0 || m > n || k <= 0 || p <= 0 || p > n) {
            return -1;
        }
        return process(n, m, k, p);

    }

    /**
     * @param n   一排有n个位置
     * @param cur 当前机器人所在位置
     * @param k   剩余k步
     * @param p   目标位置
     * @return 方法数
     */
    public static int process(int n, int cur, int k, int p) {
        if (k == 0) {
            return cur == p ? 1 : 0;
        }
        if (cur == 1) {
            return process(n, 2, k - 1, p);
        }
        if (cur == n) {
            return process(n, n - 1, k - 1, p);
        }
        return process(n, cur - 1, k - 1, p) + process(n, cur + 1, k - 1, p);
    }


    //  优化1，加入缓存表
    /*
     * ① process(4,2,4,4)  ——》  process(4,1,3,4) + process(4,3,3,4)
     * ② process(4,1,3,4) ——》 process(4,2,2,4)
     *    process(4,3,3,4) ——》 process(4,2,2,4) + process(4,4,2,4)
     * ③ process(4,2,2,4) ——》process(4,1,1,4)+process(4,3,1,4)
     *    process(4,2,2,4) ——》process(4,1,1,4)+process(4,3,1,4)
     *    process(4,4,2,4) ——》process(4,3,1,4)
     *
     * 出现大量重复计算的值，因此加入缓存表，减少重复计算
     * */


    /**
     * @param n 一排有n个位置 [1~n]
     * @param m 开始时机器人在m位置
     * @param k 机器人必须走k步
     * @param p 最终需要到的位置
     * @return 方法数
     */
    public static int robotWalkWays2(int n, int m, int k, int p) {
        if (m < 0 || m > n || k <= 0 || p <= 0 || p > n) {
            return -1;
        }
        int[][] map = new int[n + 1][k + 1];

        // 初始化map，值为-1表示当前值没有计算过
        for (int[] arr : map) {
            Arrays.fill(arr, -1);
        }
        return process2(n, m, k, p, map);

    }

    /**
     * @param n   一排有n个位置
     * @param cur 当前机器人所在位置
     * @param k   剩余k步
     * @param p   目标位置
     * @param map 缓存表
     * @return 方法数
     */
    public static int process2(int n, int cur, int k, int p, int[][] map) {
        if (map[cur][k] != -1) {
            return map[cur][k];
        }
        int ans = 0;
        if (k == 0) {
            ans = cur == p ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(n, 2, k - 1, p, map);
        } else if (cur == n) {
            ans = process2(n, n - 1, k - 1, p, map);
        } else {
            ans = process2(n, cur - 1, k - 1, p, map) + process2(n, cur + 1, k - 1, p, map);
        }
        map[cur][k] = ans;
        return ans;
    }

    // 优化3 直接对map赋值

    /**
     * @param n 一排有n个位置 [1~n]
     * @param m 开始时机器人在m位置
     * @param k 机器人必须走k步
     * @param p 最终需要到的位置
     * @return 方法数
     */
    public static int robotWalkWays3(int n, int m, int k, int p) {
        if (m < 0 || m > n || k <= 0 || p <= 0 || p > n) {
            return -1;
        }
        int[][] map = new int[n + 1][k + 1];
        map[p][0] = 1;
        for (int c = 1; c <= k; c++) {
            map[1][c] = map[2][c - 1];
            for (int i = 2; i < n; i++) {
                map[i][c] = map[i - 1][c - 1] + map[i + 1][c - 1];
            }
            map[n][c] = map[n - 1][c - 1];
        }
        return map[m][k];
    }


    public static void main(String[] args) {
        System.out.println(robotWalkWays(5, 2, 4, 4));
        System.out.println(robotWalkWays2(5, 2, 4, 4));
        System.out.println(robotWalkWays3(5, 2, 4, 4));
    }
}
