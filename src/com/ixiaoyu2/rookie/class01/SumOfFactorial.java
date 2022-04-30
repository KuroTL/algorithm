package com.ixiaoyu2.rookie.class01;

/**
 * @Author :Administrator
 * @Date :2022/2/16
 * @Description :com.msb.rookie.class01
 * @Version: 1.0
 */
public class SumOfFactorial {
    public static void main(String[] args) {
        System.out.println(getSumOfFactorial1(100));
        System.out.println(getSumOfFactorial2(100));
    }

    /**
     * 求1!~n!的和
     *
     * @param n 传入的整数n
     * @return 1！~n！的和
     */
    public static long getSumOfFactorial1(int n) {
        int factorial = 1;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i;
            sum += factorial;
        }
        return sum;
    }

    /**
     * 求1!~n!的和
     *
     * @param n 传入的整数n
     * @return 1！~n！的和
     */
    public static long getSumOfFactorial2(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += getFactorial(i);
        }
        return sum;
    }

    /**
     * 求阶乘
     *  @param n 传入的整数n
     *  @return n!
     */
    private static int getFactorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

}
