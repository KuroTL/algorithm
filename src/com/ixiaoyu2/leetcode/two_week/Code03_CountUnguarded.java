package com.ixiaoyu2.leetcode.two_week;

/**
 * 统计网格图中没有被保卫的格子数
 * https://leetcode.cn/problems/count-unguarded-cells-in-the-grid/
 *
 * @author :Administrator
 * @date :2022/4/30 0030
 */
public class Code03_CountUnguarded {
    // 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。同时给你两个二维整数数组 guards 和 walls ，
    // 其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j 座墙所在的位置。
    //一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。
    // 如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。
    //请你返回空格子中，有多少个格子是 没被保卫 的。

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] arr = new int[m][n];
        for (int[] wall : walls) {
            arr[wall[0]][wall[1]] = 1;
        }
        for (int[] guard : guards) {
            arr[guard[0]][guard[1]] = 3;
        }
        int ans = m * n - walls.length - guards.length;
        for (int i = 0; i < guards.length; i++) {
            int r = guards[i][0];
            int c = guards[i][1];
            for (int j = r; j < m - 1; j++) {
                if (arr[j + 1][c] == 0) {
                    ans--;
                    arr[j + 1][c] = 2;
                } else if (arr[j + 1][c] == 1 || arr[j + 1][c] == 3) {
                    break;
                }
            }

            for (int j = r; j > 0; j--) {
                if (arr[j - 1][c] == 0) {
                    ans--;
                    arr[j - 1][c] = 2;
                } else if (arr[j - 1][c] == 1 || arr[j - 1][c] == 3) {
                    break;
                }
            }
            for (int j = c; j < n - 1; j++) {
                if (arr[r][j + 1] == 0) {
                    ans--;
                    arr[r][j + 1] = 2;
                } else if (arr[r][j + 1] == 1 || arr[r][j + 1] == 3) {
                    break;
                }
            }

            for (int j = c; j > 0; j--) {
                if (arr[r][j - 1] == 0) {
                    ans--;
                    arr[r][j - 1] = 2;
                } else if (arr[r][j - 1] == 1 || arr[r][j - 1] == 3) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 7;
        int[][] guards = {{1, 5}, {1, 1}, {1, 6}, {0, 2}};
        int[][] walls = {{0, 6}, {0, 3}, {0, 5}};
        int i = countUnguarded(m, n, guards, walls);
        System.out.println(i);
    }
}
