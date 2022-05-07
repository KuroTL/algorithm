package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/27 0027
 */
public class Code14_BobDie {
    //给定5个参数，N，M，row，col，k
    //表示在N*M的区域上，醉汉Bob初始在(row,col)位置
    //Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
    //任何时候Bob只要离开N*M的区域，就直接死亡
    //返回k步之后，Bob还在N*M的区域的概率


    public static double livePossibility1(int n, int m, int row, int col, int k) {
        return process(n, m, row, col, k) / Math.pow(4, k);
    }

    private static long process(int n, int m, int x, int y, int rest) {
        if (x < 0 || x == n || y < 0 || y == m) {
            return 0;
        }
        if (rest == 0) {
            return 1L;
        }
        return process(n, m, x + 1, y, rest - 1) + process(n, m, x - 1, y, rest - 1) + process(n, m, x, y + 1, rest - 1) + process(n, m, x, y - 1, rest - 1);
    }


    public static double livePossibility2(int n, int m, int row, int col, int k) {
        long[][][] dp = new long[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1L;
            }
        }
        for (int h = 1; h <= k; h++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j][h] = pick(dp, n, m, i - 1, j, h - 1);
                    dp[i][j][h] += pick(dp, n, m, i + 1, j, h - 1);
                    dp[i][j][h] += pick(dp, n, m, i, j - 1, h - 1);
                    dp[i][j][h] += pick(dp, n, m, i, j + 1, h - 1);
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int n, int m, int r, int c, int rest) {
        if (r < 0 || r == n || c < 0 || c == m) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePossibility2(50, 50, 6, 6, 10));
        System.out.println(livePossibility1(50, 50, 6, 6, 10));
    }
}
