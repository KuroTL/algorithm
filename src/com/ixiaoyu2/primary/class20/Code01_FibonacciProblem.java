package com.ixiaoyu2.primary.class20;

/**
 * @author :Administrator
 * @date :2022/5/9 0009
 */
public class Code01_FibonacciProblem {

    // 斐波那契数列算法 f(1)=1,f(2)=1,f(3)=2,f(4)=3,f(5)=5……  o(n)

    public static int f(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    // O(logN)的算法  |f(n),f(n-1)| = |f(2),f(1)|*|1,1| ^ (n-2)
    //                                           |1,0|

    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] baseMatrix = {{1, 1}, {1, 0}};
        int[] coefficient = {1, 1};
        int[][] res = matrixPower(baseMatrix, n - 2);

        return coefficient[0] * res[0][0] + coefficient[1] * res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1,矩阵1次方
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, t);
            }
            t = multiMatrix(t, t);
        }
        return res;
    }

    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(f(10));
        System.out.println(f2(10));
    }
}
