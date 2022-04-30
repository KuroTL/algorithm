package com.ixiaoyu2.primary.class02;

/**
 * @Author :Administrator
 * @Date :2022/3/3
 * @Description :com.msb.primary.class02
 * @Version: 1.0
 */
public class Code01_Swap {

    /**
     * 如何不用额外变量交换两个数
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

}
