package com.ixiaoyu2.primary.code17;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/4/25 0025
 */
public class Code09_Coffee {

    //给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
    //给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
    //只有一台洗咖啡杯机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
    //每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
    //假设所有人拿到咖啡之后立刻喝干净，
    //返回从开始等到所有咖啡机变干净的最短时间
    //三个参数：int[] arr、intN，int a、int b

    /**
     * 返回从开始等到所有咖啡机变干净的最短时间
     *
     * @param arr 泡咖啡机arr[i] ，arr[i]表示i号咖啡机泡咖啡需要arr[i]的时间
     * @param n   n个人准备泡咖啡
     * @param a   洗咖啡杯机洗干净一个杯子的时间
     * @param b   自然挥发干净的时间
     * @return 返回所有人从泡咖啡到咖啡杯变干净的最短时间
     */
    public static int leastTime1(int[] arr, int n, int a, int b) {
        // 过滤无效参数
        if (arr == null || arr.length == 0 || n == 0 || a < 0 || b < 0) {
            return -1;
        }
        // 每个人最快喝完咖啡的时间点
        int[] drinkUpTime = leastMakeCoffeeTime(arr, n);
        return leastWashTime(drinkUpTime, a, b, 0, 0);
    }

    /**
     * 返回每个人喝完咖啡最短的的时间点，也就是每个人杯子可以洗或者挥发开始的时间点
     *
     * @param arr 咖啡机
     * @param n   n个人
     * @return 所有人都喝完咖啡的时间点
     */
    private static int[] leastMakeCoffeeTime(int[] arr, int n) {

        // 按咖啡机可用时间与花费时间 组织小根堆
        PriorityQueue<CoffeeMachine> coffeeMachineHeap = new PriorityQueue<>(Comparator.comparingInt(c -> (c.availableTime + c.costTime)));
        // 初始化咖啡机，可用时间都为0时刻，放入小根堆
        for (int j : arr) {
            coffeeMachineHeap.add(new CoffeeMachine(0, j));
        }

        // 每个人喝完咖啡的时间点，也就是杯子可以洗的时间
        int[] drinkUpTime = new int[n];

        for (int i = 0; i < n; i++) {
            CoffeeMachine curCoffeeMachine = coffeeMachineHeap.poll();
            // 当前咖啡机下次可用时间点是开始可用时间点加上泡完一杯咖啡的时间
            assert curCoffeeMachine != null;
            curCoffeeMachine.availableTime += curCoffeeMachine.costTime;
            // 喝咖啡时间忽略，则一个人喝完一杯咖啡就是当前咖啡机可用时间点+泡完咖啡的时间
            drinkUpTime[i] = curCoffeeMachine.availableTime;
            coffeeMachineHeap.add(curCoffeeMachine);
        }
        return drinkUpTime;
    }

    /**
     * 泡咖啡机
     */
    private static class CoffeeMachine {
        /**
         * 咖啡机可用时间
         */
        int availableTime;
        /**
         * 泡咖啡花费时间
         */
        int costTime;

        public CoffeeMachine(int availableTime, int costTime) {
            this.availableTime = availableTime;
            this.costTime = costTime;
        }
    }

    /**
     * @param drinkUpTime        所有人喝完咖啡的时间点，即时每个人可以洗杯子或挥发杯子的时间点
     * @param washCostTime       洗咖啡机洗杯子花费时间
     * @param volatilizeCostTime 自然挥发花费时间
     * @param index              当前下标
     * @param availableTime      洗咖啡机可用时间
     * @return 所有杯子变干净最短时间
     */
    private static int leastWashTime(int[] drinkUpTime, int washCostTime, int volatilizeCostTime, int index, int availableTime) {
        // 已经没有杯子要处理
        if (index == drinkUpTime.length) {
            return 0;
        }
        //可能性1：当前杯子用洗

        // 当前杯子洗完的时间点，等于可以洗的时间点+洗杯子花费时间
        // 可以洗的时间点：①杯子可以洗，单洗咖啡机不可用，需要等到咖啡机可用时间点开始;②咖啡机可用，但杯子还没喝完，需要等到杯子喝完开始
        int beCleanTime1 = Math.max(drinkUpTime[index], availableTime) + washCostTime;
        // 剩余杯子变干净的时间点
        int restCleanTime1 = leastWashTime(drinkUpTime, washCostTime, volatilizeCostTime, index + 1, beCleanTime1);
        // 所有杯子变干净的最短时间点
        int leastCostTime1 = Math.max(beCleanTime1, restCleanTime1);

        //可能性2：当前杯子用挥发
        int beCleanTime2 = drinkUpTime[index] + volatilizeCostTime;
        int restCleanTime2 = leastWashTime(drinkUpTime, washCostTime, volatilizeCostTime, index + 1, availableTime);
        int leastCostTime2 = Math.max(beCleanTime2, restCleanTime2);
        return Math.min(leastCostTime1, leastCostTime2);
    }


    // 进行动态优化

    public static int leastTime2(int[] arr, int n, int a, int b) {
        // 过滤无效参数
        if (arr == null || arr.length == 0 || n == 0 || a < 0 || b < 0) {
            return -1;
        }
        // 每个人最快喝完咖啡的时间点
        int[] drinkUpTime = leastMakeCoffeeTime(arr, n);

        return leastWashTimeDp(drinkUpTime, a, b);
    }

    private static int leastWashTimeDp(int[] drinkUpTime, int washCostTime, int volatilizeCostTime) {
        // 要洗的杯子的数量
        int n = drinkUpTime.length;
        // 洗咖啡机最晚可用时间
        int maxAvailableTime = 0;
        for (int j : drinkUpTime) {
            maxAvailableTime = Math.max(maxAvailableTime, j) + washCostTime;
        }
        int[][] dp = new int[n + 1][maxAvailableTime + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int curAvailableTime = 0; curAvailableTime <= maxAvailableTime; curAvailableTime++) {
                //可能性1：当前杯子用洗
                int beCleanTime1 = Math.max(drinkUpTime[index], curAvailableTime) + washCostTime;
                if (beCleanTime1 > maxAvailableTime) {
                    break;
                }
                int restCleanTime1 = dp[index + 1][beCleanTime1];
                int leastCostTime1 = Math.max(beCleanTime1, restCleanTime1);

                //可能性2：当前杯子用挥发
                int beCleanTime2 = drinkUpTime[index] + volatilizeCostTime;
                int restCleanTime2 = dp[index + 1][curAvailableTime];
                int leastCostTime2 = Math.max(beCleanTime2, restCleanTime2);
                dp[index][curAvailableTime] = Math.min(leastCostTime1, leastCostTime2);
            }
        }
        return dp[0][0];
    }


    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            // 当前号咖啡机
            int work = arr[i];
            // 当前咖啡机可用时间
            int pre = times[i];
            // 当前人用，当前咖啡机喝完咖啡的时间
            drink[kth] = pre + work;
            // 当前咖啡机可用时间
            times[i] = pre + work;
            // 最短时间
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            // 清空现场，当前人用下一个咖啡机
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // 以下为贪心+优良暴力
    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }


    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = leastTime1(arr, n, a, b);
            int ans3 = leastTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

}
