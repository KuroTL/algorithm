package com.ixiaoyu2.primary.class04;

/**
 * @Author :Administrator
 * @Date :2022/3/8
 * @Description :com.msb.primary.class04
 * @Version: 1.0
 */
public class Code05_CountOfRangeSum {
    //给定一个数组arr，两个整数lower和upper，返回arr中有多少个子数组的累加和在[lower,upper]范围上
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //sum数组为arr数组前缀和数组,即sum[i]=arr[0]+arr[1]+...+arr[i]
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int left, int right, int lower, int upper) {
        if (left == right) {
            return sum[left] >= lower && sum[left] <= upper ? 1 : 0;
        }
        int mid = left + ((right - left) >> 1);

        return process(sum, left, mid, lower, upper) + process(sum, mid + 1, right, lower, upper) + merge(sum, left, mid, right, lower, upper);
    }

    public static int merge(long[] sum, int left, int mid, int right, int lower, int upper) {

        //求以下标x结尾的满足条件的数组，即时0~x-1的有多少前缀和sum[i]在(sum[x]-upper,sum[x]-lower]区间

        //满足条件的范围
        int ans = 0;
        //[l,r)
        int l = left;
        int r = left;
        for (int i = mid + 1; i <= right; i++) {
            long newLower = sum[i] - upper;
            long newUpper = sum[i] - lower;

            while (r <= mid && sum[r] <= newUpper) {
                r++;
            }
            while (l <= mid && sum[l] < newLower) {
                l++;
            }
            ans += (r - l);
        }

        int p1 = left;
        int p2 = mid + 1;
        long[] help = new long[right - left + 1];
        int i = 0;
        while (p1 <= mid && p2 <= right) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= right) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[left + i] = help[i];
        }
        return ans;
    }


}
