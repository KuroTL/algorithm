package com.ixiaoyu2.primary.class17;

import java.util.HashMap;

/**
 * @author :Administrator
 * @date :2022/4/27 0027
 */
public class Code13_CoindWaysSameValueSamePaper {
    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //认为值相同的货币没有任何不同，
    //返回组成aim的方法数
    //例如：arr = {1,2,1,1,2,1,2}，aim = 4
    //方法：1+1+1+1、1+1+2、2+2
    //一共就3种方法，所以返回3


    public static int differentWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }

        Info info = getInfo(arr);
        return process(info.coins, info.count, 0, aim);
    }

    private static int process(int[] vales, int[] count, int index, int rest) {
        if (index == vales.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; (i <= count[index]) && (rest - vales[index] * i >= 0); i++) {
            ans += process(vales, count, index + 1, rest - vales[index] * i);
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
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] counts = info.count;
        int n = counts.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                for (int k = 0; k <= counts[i] && (j - coins[i] * k >= 0); k++) {
                    dp[i][j] += dp[i + 1][j - coins[i] * k];
                }
            }

        }
        return dp[0][aim];
    }

/*
    对动态规划表，每个位置的枚举行为进行优化
    coins[i]=m,count[i] = k,j-km>=0
    dp[i][j] = dp[i + 1][j]+dp[i+1][j-m]+dp[i+1][j-2m]+……+dp[i+1][j-km]

    dp[i][j-m] = dp[i+1][j-m]+dp[i+1][j-2m]+……+dp[i+1][j-m-km]

  ==> dp[i][j] = dp[i+1][j]+dp[i][j-m]-dp[i+1][j-m-km]
*/


    public static int differentWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] count = info.count;
        int n = count.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
                if (j - (coins[i] * (count[i] + 1)) >= 0) {
                    dp[i][j] -= dp[i + 1][j - coins[i] * (count[i] + 1)];
                }
            }
        }

        return dp[0][aim];
    }


    private static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        int[] values = new int[map.size()];
        int[] count = new int[map.size()];
        final int[] i = {0};
        map.forEach((val, num) -> {
            values[i[0]] = val;
            count[i[0]++] = num;
        });
        return new Info(values, count);
    }

    private static class Info {
        int[] coins;
        int[] count;

        public Info(int[] coins, int[] count) {
            this.coins = coins;
            this.count = count;
        }
    }


    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] count = info.count;
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
                if (j - coins[i] * (count[i] + 1) >= 0) {
                    dp[i][j] -= dp[i + 1][j - coins[i] * (count[i] + 1)];
                }
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = differentWays1(arr, aim);
            int ans2 = differentWays2(arr, aim);
            int ans3 = differentWays3(arr, aim);

            int ans0 = dp2(arr, aim);
            if (ans1 != ans0 || ans0 != ans2 || ans0 != ans3) {
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
