package com.ixiaoyu2.primary.code17;

/**
 * @author :Administrator
 * @date :2022/4/27 0027
 */
public class Code12_CoindWayNoLimit {

    //arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。每个值都认为是一种面值，且认为张数是无限的。返回组成aim的方法数
    //例如：arr={1,2}，aim=4方法如下：1+1+1+1、1+1+2、2+2，一共就3种方法，所以返回3

    public static int differentWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        return process(arr, aim, 0);
    }

    private static int process(int[] arr, int rest, int index) {

        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; rest - arr[index] * i >= 0; i++) {
            ans += process(arr, rest - arr[index] * i, index + 1);
        }
        return ans;
    }

    // 暴力递归改动态规划


    public static int differentWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    dp[i][j] += dp[i + 1][j - arr[i] * k];
                }
            }
        }
        return dp[0][aim];
    }



/*
 对上面递归 每个位置的枚举行为进行分析
 int m = arr[i],j-(k+1)m<0,则：
 dp[i][j] = dp[i+1][j]+dp[i+1][j-m]+dp[i+1][j-2m]+dp[i+1][j-3m]+……+dp[i+1][j-km]

 dp[i][j-m] = dp[i+1][j-m]+dp[i+1][j-2m]+dp[i+1][j-3m]+……+dp[i+1][j-km]

 因此，dp[i][j]的枚举行为可以被dp[i][j-m]代替  =》 dp[i][j]=dp[i+1][j]+dp[i][j-m]
 */

    public static int differentWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] += dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }


    // 老师的代码 昨晚对数器

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = differentWays1(arr, aim);
            int ans2 = differentWays2(arr, aim);
            int ans3 = differentWays3(arr, aim);

            int ans0 = dp2(arr, aim);
            if (ans1 != ans0 || ans2 != ans0) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans0);

                break;
            }
        }
        System.out.println("测试结束");
    }
}
