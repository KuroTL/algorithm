package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/27 0027
 */
public class Code11_CoinsWayEveryPaperDifferent {

    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //即便是值相同的货币也认为每一张都是不同的，
    //返回组成aim的方法数
    //例如：arr = {1,1,1}，aim = 2
    //第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
    //一共就3种方法，所以返回3

    public static int differentWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        return process(arr, aim, 0);
    }

    private static int process(int[] arr, int rest, int i) {
        if (rest < 0) {
            return 0;
        }
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            int p1 = process(arr, rest, i + 1);
            int p2 = 0;
            p2 = process(arr, rest - arr[i], i + 1);
            return p1 + p2;
        }
    }


    // 对暴力递归改动态规划

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
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }

        return dp[0][aim];
    }

    // 以上递归压缩空间

    public static int differentWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }

        int n = arr.length;
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                dp[j] += (j - arr[i] >= 0 ? dp[j - arr[i]] : 0);
            }
        }
        return dp[aim];
    }


    public static int dp0(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = differentWays(arr, aim);
            int ans2 = differentWays2(arr, aim);
            int ans3 = differentWays3(arr, aim);
            int ans0 = dp0(arr, aim);
            if (ans1 != ans0 || ans0 != ans2 || ans0 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans0);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
