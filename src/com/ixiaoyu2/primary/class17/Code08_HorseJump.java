package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/25 0025
 */
public class Code08_HorseJump {

    //请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
    //那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
    //给你三个 参数 x，y，k
    //返回“马”从(0,0)位置出发，必须走k步
    //最后落在(x,y)上的方法数有多少种?


    /**
     * 返回
     *
     * @param x 横坐标上9条线，则x范围 0~8
     * @param y 纵坐标上10条线，则y范围 0~9
     * @param k 走的步数
     * @return 返回从(0, 0)出发，走到(x,y)的方法
     */
    public static int horseJump1(int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8 || k == 0) {
            return 0;
        }
        return process(x, y, 0, 0, k);
    }

    /**
     * @param x 目标
     * @param y 目标
     * @param i 当前横坐标
     * @param j 当前纵坐标
     * @param k 剩余步数
     * @return 返回从当前位置(i, j)，走k步，走到(x,y)的方法数
     */
    private static int process(int x, int y, int i, int j, int k) {
        // 跳到棋盘外
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        // 剩余0步，当前位置是目标位置，返回1种走法
        if (k == 0) {
            return i == x && j == y ? 1 : 0;
        }
        // 其余位置有8种可能性
        int ans = 0;
        ans += process(x, y, i + 1, j + 2, k - 1);
        ans += process(x, y, i + 2, j + 1, k - 1);
        ans += process(x, y, i + 2, j - 1, k - 1);
        ans += process(x, y, i + 1, j - 2, k - 1);
        ans += process(x, y, i - 1, j + 2, k - 1);
        ans += process(x, y, i - 2, j + 1, k - 1);
        ans += process(x, y, i - 2, j - 1, k - 1);
        ans += process(x, y, i - 1, j - 2, k - 1);
        return ans;
    }

    // 暴力递归改动态规划
    public static int horseJump2(int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || k == 0) {
            return 0;
        }

        int[][][] dp = new int[10][9][k + 1];
        dp[x][y][0] = 1;
        for (int k0 = 1; k0 <= k; k0++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][k0] += pick(dp, i + 1, j + 2, k0 - 1);
                    dp[i][j][k0] += pick(dp, i + 2, j + 1, k0 - 1);
                    dp[i][j][k0] += pick(dp, i + 2, j - 1, k0 - 1);
                    dp[i][j][k0] += pick(dp, i + 1, j - 2, k0 - 1);
                    dp[i][j][k0] += pick(dp, i - 1, j + 2, k0 - 1);
                    dp[i][j][k0] += pick(dp, i - 2, j + 1, k0 - 1);
                    dp[i][j][k0] += pick(dp, i - 2, j - 1, k0 - 1);
                    dp[i][j][k0] += pick(dp, i - 1, j - 2, k0 - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    private static int pick(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(horseJump1(x, y, step));
        System.out.println(horseJump2(x, y, step));

    }
}
