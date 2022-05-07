package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/5/1 0001
 */
public class Code17_SplitNumber {

    //给定一个正数n，求n的裂开方法数，
    //规定：后面的数不能比前面的数小
    //比如4的裂开方法有：
    //1+1+1+1、1+1+2、1+3、2+2、4
    //5种，所以返回5

    public static int splitNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    private static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        } else {
            int ans = 0;
            for (int i = pre; i <= rest; i++) {
                ans += process(i, rest - i);
            }
            return ans;
        }
    }

    // 以上递归改dp

    public static int splitNumber2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ans = 0;
                for (int i = pre; i <= rest; i++) {
                    ans += dp[i][rest - i];
                }
                dp[pre][rest] = ans;
            }

        }
        return process(1, n);
    }


    // 以上递归枚举行为进行优化

    public static int splitNumber3(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }


    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 15;
        System.out.println(splitNumber(test));
        System.out.println(splitNumber2(test));
//        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }

}
