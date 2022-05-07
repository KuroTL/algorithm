package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/27 0027
 */
public class Code10_MiniPathSum {

    //给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
    //沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
    //返回最小距离累加和


    public static int miniPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        return process1(matrix, matrix.length - 1, matrix[0].length - 1, 0, 0);
    }


    /**
     * 从当前位置matrix[i][j],到右下角matrix[x][y]的最短距离累加和
     *
     * @param matrix 二维数组
     * @param i      当前行
     * @param j      当前列
     * @param x      最后一行
     * @param y      最后一列
     * @return 最短距离累加和
     */
    private static int process1(int[][] matrix, int x, int y, int i, int j) {
        if (i == x && j == y) {
            return matrix[x][y];
        }
        if (i == x && j < y) {
            return process1(matrix, x, y, i, j + 1) + matrix[i][j];
        }
        if (j == y && i < x) {
            return process1(matrix, x, y, i + 1, j) + matrix[i][j];
        }
        return matrix[i][j] + Math.min(process1(matrix, x, y, i, j + 1), process1(matrix, x, y, i + 1, j));
    }


    //给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
    //沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
    //返回最小距离累加和


    public static int miniPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        return process2(matrix, matrix.length - 1, matrix[0].length - 1);
    }


    /**
     * 从当前位置matrix[0][0],到matrix[x][y]的最短距离累加和
     *
     * @param matrix 二维数组
     * @param x      最后一行
     * @param y      最后一列
     * @return 最短距离累加和
     */
    private static int process2(int[][] matrix, int x, int y) {
        if (x == 0 && y == 0) {
            return matrix[0][0];
        }
        if (x == 0) {
            return matrix[0][y] + process2(matrix, 0, y - 1);
        }
        if (y == 0) {
            return matrix[x][0] + process2(matrix, x - 1, 0);
        }

        return matrix[x][y] + Math.min(process2(matrix, x, y - 1), process2(matrix, x - 1, y));
    }


    // 第一种暴力递归和第二种暴力递归其实是一类，从左向右尝试模型，把其中一种改为动态规划

    public static int miniPathSum3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        return dp[n - 1][m - 1];
    }

    //对于以上递归，dp表可以进行压缩
    public static int miniPathSum4(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int len = Math.min(m, n);
        int[] dp = new int[len];
        dp[0] = matrix[0][0];
        if (len == m) {
            for (int j = 1; j < m; j++) {
                dp[j] = dp[j - 1] + matrix[0][j];
            }
            for (int i = 1; i < n; i++) {
                dp[0] += matrix[i][0];
                for (int j = 1; j < m; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + matrix[i][j];
                }
            }
        } else {
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i - 1] + matrix[i][0];
            }
            for (int j = 1; j < n; j++) {
                dp[0] += matrix[0][j];
                for (int i = 1; i < m; i++) {
                    dp[i] = Math.min(dp[i], dp[i - 1]) + matrix[i][i];
                }
            }
        }
        return dp[len - 1];
    }


    // 对数器 老师写的方法
    public static int minPathSum0(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int testTimes = 1000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            int[][] m = generateRandomMatrix(rowSize, colSize);
            int an1 = miniPathSum1(m);
            int ans2 = miniPathSum2(m);
            int ans3 = miniPathSum3(m);
            int ans4 = miniPathSum4(m);
            int ans0 = minPathSum0(m);
            if (ans0 != an1 || ans0 != ans2 || ans0 != ans3 || ans0 != ans4) {
                System.out.println("出错");
                printMatrix(m);
                System.out.println(miniPathSum1(m));
                System.out.println(miniPathSum2(m));
                System.out.println(miniPathSum3(m));
                System.out.println(miniPathSum4(m));
                System.out.println(minPathSum0(m));
                break;
            }
        }
        System.out.println("结束");
    }
}
