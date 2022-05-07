package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/5/1 0001
 */
public class Code18_SplitArrayClosed {

    //给定一个正数数组arr，请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
    //返回：最接近的情况下，较小集合的累加和


    public static int closedArrSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        int target = sum / 2;

        return process(arr, 0, target);
    }

    private static int process(int[] arr, int index, int target) {
        if (target == 0) {
            return 0;
        } else {
            if (index == arr.length) {
                return 0;
            } else {
                int p1 = process(arr, index + 1, target);
                int p2 = 0;
                if (target - arr[index] >= 0) {
                    p2 = arr[index] + process(arr, index + 1, target - arr[index]);
                }
                return Math.max(p1, p2);
            }
        }
    }


    // 以上递归改动态规划


    public static int closedArrSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        int target = sum / 2;

        int n = arr.length;
        int[][] dp = new int[n + 1][target + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = target; j >= 0; j--) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (j - arr[i] >= 0) {
                    p2 = arr[i] + dp[i + 1][j - arr[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][target];
    }


    // 以下为老师代码用于测试

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = closedArrSum(arr);
            int ans2 = closedArrSum2(arr);
            int ans0 = dp(arr);
            if (ans1 != ans0 || ans2 != ans0) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans0);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
