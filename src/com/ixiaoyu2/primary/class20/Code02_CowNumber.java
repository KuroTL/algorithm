package com.ixiaoyu2.primary.class20;

/**
 * @author :Administrator
 * @date :2022/5/10 0010
 */
public class Code02_CowNumber {

    //第一年农场有1只成熟的母牛A，往后的每年：
    //1）每一只成熟的母牛都会生一只母牛
    //2）每一只新出生的母牛都在出生的第三年成熟
    //3）每一只母牛永远不会死
    //返回N年后牛的数量

    // f(n) = f(n-1)+f(n-3)

    public static int cowNumber(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }

        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[] coefficient = {3, 2, 1};
        int[][] res = matrixPower(base, n - 3);
        return coefficient[0] * res[0][0] + coefficient[1] * res[1][0] + coefficient[2] * res[2][0];
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
        System.out.println(cowNumber(4));
        System.out.println(cowNumber(5));
        System.out.println(cowNumber(6));
        System.out.println(cowNumber(7));
    }
}
