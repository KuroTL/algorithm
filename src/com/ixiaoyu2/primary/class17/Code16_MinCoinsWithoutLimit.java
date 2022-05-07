package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/29 0029
 */
public class Code16_MinCoinsWithoutLimit {

    //arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。每个值都认为是一种面值，且认为张数是无限的。
    // 返回组成aim的最少货币数。

    public static int minCoins1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        return process(arr, aim, 0);
    }


    /**
     * 当前选择第i张货币，剩余目标金额restAim,返回需要的最少货币数
     */
    private static int process(int[] arr, int restAim, int index) {
        // 已经没有货币可以选，如果此时剩余目标金额为0，则需要0张，如果不为0，返回最大int值
        if (index == arr.length) {
            return restAim == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (restAim == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; restAim - arr[index] * i >= 0; i++) {
            int left = process(arr, restAim - arr[index] * i, index + 1);
            if (left != Integer.MAX_VALUE) {
                ans = Math.min(ans, i + left);
            }
        }
        return ans;
    }


    // 对以上暴力递归改动态规划


    public static int minCoins2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                int ans = Integer.MAX_VALUE;
                for (int i = 0; restAim - arr[index] * i >= 0; i++) {
                    if (dp[index + 1][restAim - arr[index] * i] != Integer.MAX_VALUE) {
                        ans = Math.min(ans, i + dp[index + 1][restAim - arr[index] * i]);
                    }
                }
                dp[index][restAim] = ans;
            }
        }
        return dp[0][aim];
    }

    /*
     * 对以上dp中的枚举行为进行优化
     *
     * j-(n+1)arr[i]<0
     * 0<=x<=n
     * 需满足：dp[i + 1][j - arr[i] * x] != Integer.MAX_VALUE
     *
     * Math.min() = a
     *
     * dp[i][j] = Math.min(Integer.MAX_VALUE，dp[i+1][j]，dp[i+1][j-arr[i]]+1,dp[i+1][j-2*arr[i]]+2,……,dp[i+1][j-n*arr[i]]+n))
     *
     *
     *
     *
     * ap[i][j-arr[i]]!=Integer.MAX_VALUE
     *
     * dp[i][j-arr[i]) = Math.min(Integer.MAX_VALUE，dp[i+1][j-arr[i]) , dp[i+1][j-2arr[i]]+1,  dp[i+1][j-3*arr[i]]+2  ,……,  dp[i+1][j-n*arr[i]]+n-1))
     *
     * ==》dp[i][j-arr[i])+1 = Math.min(Integer.MAX_VALUE，dp[i+1][j-arr[i])+1 , dp[i+1][j-2arr[i]]+2,  dp[i+1][j-3*arr[i]]+3  ,……,  dp[i+1][j-n*arr[i]]+n))
     *
     *
     * ==》dp[i][j] = Math.min(Integer.MAX_VALUE,dp[i+1][j],dp[i][j-arr[i])+1)
     *
     *
     *
     * */

    public static int minCoins3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                dp[index][restAim] = dp[index + 1][restAim];
                if (restAim - arr[index] >= 0 && dp[index][restAim - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][restAim] = Math.min(dp[index][restAim], 1 + dp[index][restAim - arr[index]]);
                }
            }
        }
        return dp[0][aim];
    }


    // 对数器，用于测试

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins1(arr, aim);
            int ans2 = minCoins2(arr, aim);
            int ans3 = minCoins3(arr, aim);
            int ans0 = dp2(arr, aim);
            if (ans1 != ans0 || ans2 != ans0 || ans3 != ans0) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("aim:" + aim);
                System.out.println("ans1:" + ans1);
                System.out.println("ans2:" + ans2);
                System.out.println("ans3:" + ans3);
                System.out.println("ans0:" + ans0);
                break;
            }
        }
        System.out.println("功能测试结束");
    }


}
