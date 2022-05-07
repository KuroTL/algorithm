package com.ixiaoyu2.primary.class17;

import java.math.BigDecimal;

/**
 * @author :Administrator
 * @date :2022/4/29 0029
 */
public class Code15_KillMonster {

    //给定3个参数，N，M，K
    //怪兽有N滴血，等着英雄来砍自己
    //英雄每一次打击，都会让怪兽流失[0~M]的血量
    //到底流失多少？每一次在[0~M]上等概率的获得一个值
    //求K次打击之后，英雄把怪兽砍死的概率


    /**
     * k次打击后，怪兽死亡的概率
     *
     * @param n 怪兽hp
     * @param m 最大伤害，伤害0~m
     * @param k k次攻击
     * @return 怪兽死亡概率
     */
    public static double possibility1(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        return process(n, m, k) / Math.pow(m + 1, k);
    }

    /**
     * 怪兽当前hp血量，剩余rest攻击次数，每次攻击伤害0~m,返回hp<=0的次数
     */
    public static long process(int hp, int m, int rest) {
        if (rest == 0) {
            return hp == 0 ? 1L : 0;
        }
        if (hp == 0) {
            return (long) Math.pow(m + 1, rest);
        }

        long ans = 0;
        for (int i = 0; i <= m; i++) {
            ans += process(Math.max(hp - i, 0), m, rest - 1);
        }
        return ans;
    }


    // 对以上暴力递归改动态规划

    public static double possibility2(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }

        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= k; i++) {
            dp[0][i] = (long) Math.pow(m + 1, i);
        }

        for (int hp = 1; hp <= n; hp++) {
            for (int rest = 1; rest <= k; rest++) {
                for (int i = 0; i <= m; i++) {
                    dp[hp][rest] += dp[Math.max(hp - i, 0)][rest - 1];
                }
            }
        }

        return dp[n][k] / Math.pow(m + 1, k);
    }

    /* 以上dp表每个表格存在枚举行为，如何优化？找规律

        dp[i][j] = dp[i][j-1]+dp[i-1][j-1]+dp[i-1][j-1]+……+dp[i-m][j-1]

        dp[i-1][j] = dp[i-1][j-1]+dp[i-2][j-1]+dp[i-3][j-1]+……+dp[i-m][j-1]+dp[i-m-1][j-1]

        dp[i][j] = dp[i][j-1]+dp[i-1][j]+dp[i-m][j-1]-dp[i-m-1][j-1]
     */


    public static double possibility3(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }

        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= k; i++) {
            dp[0][i] = (long) Math.pow(m + 1, i);
        }

        for (int hp = 1; hp <= n; hp++) {
            for (int rest = 1; rest <= k; rest++) {
                dp[hp][rest] = dp[hp][rest - 1] + dp[hp - 1][rest] - dp[Math.max(hp - m - 1, 0)][rest - 1];
            }
        }

        return dp[n][k] / Math.pow(m + 1, k);
    }


    //  以下为对数器用于测试

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            BigDecimal ans1 = BigDecimal.valueOf(possibility1(N, M, K));
            BigDecimal ans2 = BigDecimal.valueOf(possibility2(N, M, K));
            BigDecimal ans3 = BigDecimal.valueOf(possibility3(N, M, K));
            BigDecimal ans0 = BigDecimal.valueOf(dp2(N, M, K));

            if (ans1.compareTo(ans0) != 0 || ans2.compareTo(ans0) != 0 || ans3.compareTo(ans0) != 0) {
                System.out.println("Oops!");
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
