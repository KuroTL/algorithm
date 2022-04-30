package com.ixiaoyu2.primary.class13;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/4/9 0009
 */
public class Code05_BestProfit {
    /*输入: 正数数组costs、正数数组profits、正数K、正数M
    costs[i]表示i号项目的花费
    profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
    K表示你只能串行的最多做k个项目
    M表示你初始的资金
    说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
    输出：你最后获得的最大钱数。
    */

    public static int bestProfit(int[] costs, int[] profits, int k, int m) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        // 根据利润的大根堆
        PriorityQueue<Project> maxFrofitsHeap = new PriorityQueue<>((one, two) -> two.profit - one.profit);
        // 根据成本的小根堆
        PriorityQueue<Project> minCostHeap = new PriorityQueue<>(Comparator.comparingInt(project -> project.cost));

        for (int i = 0; i < costs.length; i++) {
            minCostHeap.add(new Project(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= m) {
                maxFrofitsHeap.add(minCostHeap.poll());
            }
            if (maxFrofitsHeap.isEmpty()) {
                return m;
            }
            m += maxFrofitsHeap.poll().profit;
        }
        return m;
    }

    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}
