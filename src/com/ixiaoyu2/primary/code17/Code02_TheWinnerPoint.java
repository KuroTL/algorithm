package com.ixiaoyu2.primary.code17;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/4/20 0020
 */
public class Code02_TheWinnerPoint {

    //给定一个整型数组arr，代表数值不同的纸牌排成一条线,玩家A和玩家B依次拿走每张纸牌
    //规定玩家A先拿，玩家B后拿,但是每个玩家每次只能拿走最左或最右的纸牌
    //玩家A和玩家B都绝顶聪明,请返回最后获胜者的分数。

    public static int winnerPoint1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        System.out.println("先手分数：" + first + "  后手分数：" + second);
        System.out.println(first > second ? "先手获胜" : "后手获胜");
        return Math.max(first, second);
    }

    /**
     * 表示先手时获取的最大分数
     *
     * @param arr 纸牌数组
     * @param l   数组最左边
     * @param r   数组最右边
     * @return 获取的最大分数
     */
    private static int f1(int[] arr, int l, int r) {
        // 只有1张牌，先手就获得这张牌
        if (l == r) {
            return arr[l];
        }
        // 先手 获得左边的牌的分数和获得右边牌的分数的最大值
        int left = arr[r] + g1(arr, l, r - 1);
        int right = arr[l] + g1(arr, l + 1, r);
        return Math.max(left, right);
    }

    /**
     * 表示后手时获得的最大分数
     *
     * @param arr 纸牌数组
     * @param l   数组最左边
     * @param r   数组最右边
     * @return 获取的最大分数
     */
    private static int g1(int[] arr, int l, int r) {
        // 只有1张牌，后手获得分数为0
        if (l == r) {
            return 0;
        }
        // 表示先手获取了左边的排，在剩下的范围获取最大值
        int left = f1(arr, l + 1, r);
        // 表示先手获取了右边的排，在剩下的范围获取最大值
        int right = f1(arr, l, r - 1);
        // 因为是后手，只能获得两者分数小的
        return Math.min(left, right);
    }

    // 优化1
    /*  以 int[] a = {11, 15, 22, 3} 为例进行分析
     *  先手：f(a,0,3) = Math.max(a[0]+g(a,1,3),a[3]+g(a,0,2))
     *
     *      g(a,1,3) = Math.min(f(a,2,3),f(a,1,2))
     *
     *             f(a,2,3) = Math.max(a[2]+g(a,3,3),a[3]+g(a,2,2))
     *             f(a,1,2) = Matj.max(a[1]+g(a,2,2),a[2]+g(a,1,1))
     *
     *      g(a,0,2) = Math.min(f(a,0,1),f(a,1,2))
     *
     *             f(a,0,1)= Math.mxa(a[0]+g(a,1,1),a[1]+g(a,0,0)
     *             f(a,1,2)= Math.mxa(a[1]+g(a,2,2),a[2]+g(a,1,1)
     *
     *  后手：g(a,0,3) = Math.min(f(a,1,3),f(a,0,2))
     *
     *      f(a,1,3) = Math.max(a[1]+g(a,2,3),a[3]+g(a,1,2))
     *
     *             g(a,2,3) = Math.min(f(a,3,3),f(a,2,2)
     *             g(a,1,2) = Math.min(f(a,1,1),f(a,2,2)
     *
     *      f(a,0,2) = Math.max(a[0]+g(a,1,2),a[2]+g(a,0,1))
     *
     *             g(a,1,2) = Math.min(f(a,1,1),f(a,2,2)
     *             g(a,0,1) = Math.min(f(a,0,0),f(a,1,1)
     *
     * 通过以上分析，有重复计算的值，因此加入缓存表，避免重复计算
     * */


    public static int winnerPoint2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] firstMap = new int[arr.length][arr.length];
        int[][] secondMap = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(firstMap[i], -1);
            Arrays.fill(secondMap[i], -1);
        }

        int first = f2(arr, 0, arr.length - 1, firstMap, secondMap);
        int second = g2(arr, 0, arr.length - 1, firstMap, secondMap);
        System.out.println("先手分数：" + first + "  后手分数：" + second);
        System.out.println(first > second ? "先手获胜" : "后手获胜");
        return Math.max(first, second);
    }

    /**
     * 表示先手时获取的最大分数
     *
     * @param arr 纸牌数组
     * @param l   数组最左边
     * @param r   数组最右边
     * @return 获取的最大分数
     */
    private static int f2(int[] arr, int l, int r, int[][] firstMap, int[][] secondMap) {

        if (firstMap[l][r] != -1) {
            return firstMap[l][r];
        }
        // 只有1张牌，先手就获得这张牌
        int ans = -1;
        if (l == r) {
            ans = arr[l];
        } else {
            // 先手 获得左边的牌的分数和获得右边牌的分数的最大值
            int left = arr[r] + g1(arr, l, r - 1);
            int right = arr[l] + g1(arr, l + 1, r);
            ans = Math.max(left, right);
        }
        firstMap[l][r] = ans;
        return ans;
    }

    /**
     * 表示后手时获得的最大分数
     *
     * @param arr 纸牌数组
     * @param l   数组最左边
     * @param r   数组最右边
     * @return 获取的最大分数
     */
    private static int g2(int[] arr, int l, int r, int[][] firstMap, int[][] secondMap) {

        if (secondMap[l][r] != -1) {
            return secondMap[l][r];
        }
        int ans = -1;
        // 只有1张牌，后手获得分数为0
        if (l == r) {
            ans = 0;
        } else {
            // 表示先手获取了左边的排，在剩下的范围获取最大值
            int left = f1(arr, l + 1, r);
            // 表示先手获取了右边的排，在剩下的范围获取最大值
            int right = f1(arr, l, r - 1);
            // 因为是后手，只能获得两者分数小的
            ans = Math.min(left, right);
        }
        secondMap[l][r] = ans;
        return ans;
    }

    // 优化3 直接对map赋值

    public static int winnerPoint3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] firstMap = new int[n][n];
        int[][] secondMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            firstMap[i][i] = arr[i];
        }

        for (int col = 1; col < n; col++) {
            for (int l = 0, r = col; r < n; l++, r++) {
                secondMap[l][r] = Math.min(firstMap[l][r - 1], firstMap[l + 1][r]);
                firstMap[l][r] = Math.max(arr[l] + secondMap[l + 1][r], arr[r] + secondMap[l][r - 1]);
            }
        }
        int first = firstMap[0][n - 1];
        int second = secondMap[0][n - 1];
        System.out.println("先手分数：" + first + "  后手分数：" + second);
        System.out.println(first > second ? "先手获胜" : "后手获胜");
        return Math.max(first, second);
    }


    public static void main(String[] args) {
        int[] a = {15, 36, 22, 3};
        System.out.println(winnerPoint1(a));
        System.out.println("+================");
        System.out.println(winnerPoint2(a));
        System.out.println("+================");
        System.out.println(winnerPoint3(a));
    }
}
