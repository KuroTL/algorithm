package com.ixiaoyu2.primary.class18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author :Administrator
 * @date :2022/5/7 0007
 */
public class Code04_MinCoinsOnePaper {
    //arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，返回组成aim的最少货币数
    //注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了

    public static int minCoins1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        return process1(arr, aim, 0);
    }

    private static int process1(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }

        int p1 = process1(arr, rest, index + 1);
        int p2 = Integer.MAX_VALUE;
        int next = Integer.MAX_VALUE;
        if (rest - arr[index] >= 0) {
            next = Math.min(next, process1(arr, rest - arr[index], index + 1));
        }
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }

    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = aim; rest >= 0; rest--) {
                int p1 = dp[index + 1][rest];
                int p2 = Integer.MAX_VALUE;
                int next = Integer.MAX_VALUE;
                if (rest - arr[index] >= 0) {
                    next = dp[index + 1][rest - arr[index]];
                }
                if (next != Integer.MAX_VALUE) {
                    p2 = 1 + next;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }


    public static int minCoins2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);

        return process2(info.coins, info.zhangs, 0, aim);
    }

    private static int process2(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= zhangs[index] && rest - coins[index] * i >= 0; i++) {
            int next = process2(coins, zhangs, index + 1, rest - coins[index] * i);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + i);
            }
        }
        return ans;
    }

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;

        int[][] dp = new int[n + 1][aim + 1];

        for (int rest = 1; rest <= aim; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = aim; rest >= 0; rest--) {
                int ans = Integer.MAX_VALUE;
                for (int i = 0; i <= zhangs[index] && rest - coins[index] * i >= 0; i++) {
                    int next = dp[index + 1][rest - coins[index] * i];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + i);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }


    public static int dp3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;

        int[][] dp = new int[n + 1][aim + 1];

        for (int rest = 1; rest <= aim; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
                // 当前面值 x
                // mod  mod + x   mod + 2*x   mod + 3 * x
                LinkedList<Integer> w = new LinkedList<>();
                w.add(mod);

                // 0~mod,mod < coins[index]
                dp[index][mod] = dp[index + 1][mod];

                //
                for (int rest = mod + coins[index]; rest <= aim; rest += coins[index]) {
                    while (!w.isEmpty() && (dp[index + 1][w.peekLast()] == Integer.MAX_VALUE
                            || dp[index + 1][w.peekLast()] + compensate(w.peekLast(), rest, coins[index]) >= dp[index + 1][rest])) {
                        w.pollLast();
                    }
                    w.addLast(rest);
                    int overdue = rest - coins[index] * (zhangs[index] + 1);
                    
                    if (w.peekFirst() == overdue) {
                        w.pollFirst();
                    }
                    dp[index][rest] = dp[index + 1][w.peekFirst()] + compensate(w.peekFirst(), rest, coins[index]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }


    // for test
    // dp2时间复杂度为：O(arr长度) + O(货币种数 * aim * 每种货币的平均张数)
    public static int dptest(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        // 得到info时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 这三层for循环，时间复杂度为O(货币种数 * aim * 每种货币的平均张数)
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int zhang = 1; zhang * coins[index] <= aim && zhang <= zhangs[index]; zhang++) {
                    if (rest - zhang * coins[index] >= 0
                            && dp[index + 1][rest - zhang * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], zhang + dp[index + 1][rest - zhang * coins[index]]);
                    }
                }
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
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
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
//            int ans1 = minCoins2(arr, aim);?
//            int ans2 = dp1(arr, aim);
            int ans0 = dptest(arr, aim);
//            int ans4 = dp2(arr, aim);
            int ans5 = dp3(arr, aim);
            if (ans5 != ans0) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans5);
//                System.out.println(ans4);
//                System.out.println(ans2);
                System.out.println(ans0);
//                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");
//
//        System.out.println("==========");
//
//        int aim = 0;
//        int[] arr = null;
//        long start;
//        long end;
//        int ans2;
//        int ans3;

//        System.out.println("性能测试开始");
//        maxLen = 30000;
//        maxValue = 20;
//        aim = 60000;
//        arr = randomArray(maxLen, maxValue);
//
//        start = System.currentTimeMillis();
//        ans2 = dp2(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + " ms");
//
//        start = System.currentTimeMillis();
//        ans3 = dp3(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + " ms");
//        System.out.println("性能测试结束");
//
//        System.out.println("===========");
//
//        System.out.println("货币大量重复出现情况下，");
//        System.out.println("大数据量测试dp3开始");
//        maxLen = 20000000;
//        aim = 10000;
//        maxValue = 10000;
//        arr = randomArray(maxLen, maxValue);
//        start = System.currentTimeMillis();
//        ans3 = dp3(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp3运行时间 : " + (end - start) + " ms");
//        System.out.println("大数据量测试dp3结束");
//
//        System.out.println("===========");
//
//        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
//        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
//        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }
}
