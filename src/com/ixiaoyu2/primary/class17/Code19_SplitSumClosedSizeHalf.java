package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/5/2 0002
 */
public class Code19_SplitSumClosedSizeHalf {

    //给定一个正数数组arr，请把arr中所有的数分成两个集合，
    // 如果arr长度为偶数，两个集合包含数的个数要一样多，
    // 如果arr长度为奇数，两个集合包含数的个数必须只差一个，请尽量让两个集合的累加和接近
    //返回：最接近的情况下，较小集合的累加和


    public static int closedSum(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int target = sum / 2;

        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, target);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, target), process(arr, 0, arr.length / 2 + 1, target));
        }

    }

    private static int process(int[] arr, int i, int picks, int target) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process(arr, i + 1, picks, target);
            // 就是要使用arr[i]这个数
            int p2 = -1;
            int next = -1;
            if (arr[i] <= target) {
                next = process(arr, i + 1, picks - 1, target - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }

    }

    // 改动态规划
    public static int closedSum2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int target = sum / 2;
        int n = arr.length;
        int picks = (n + 1) / 2;
        int[][][] dp = new int[n + 1][picks + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= picks; j++) {
                for (int k = 0; k <= target; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int rest = 0; rest <= target; rest++) {
            dp[n][0][rest] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= picks; j++) {
                for (int k = 0; k <= target; k++) {
                    int p1 = dp[i + 1][j][k];
                    // 就是要使用arr[i]这个数
                    int p2 = -1;
                    int next = -1;
                    if (j - 1 >= 0 && arr[i] <= k) {
                        next = dp[i + 1][j - 1][k - arr[i]];
                    }
                    if (next != -1) {
                        p2 = arr[i] + next;
                    }
                    dp[i][j][k] = Math.max(p1, p2);
                }
            }
        }


        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][target];
        } else {
            return Math.max(dp[0][arr.length / 2][target], dp[0][arr.length / 2 + 1][target]);
        }
    }

    //以下代码为老师代码用于测试

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = closedSum(arr);
            int ans2 = closedSum2(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
