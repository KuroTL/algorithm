package com.ixiaoyu2.primary.class02;

/**
 * @Author :Administrator
 * @Date :2022/3/3
 * @Description :com.msb.primary.class02
 * @Version: 1.0
 */
public class Code3_NearRightOne {
    /**
     * 一个int类型的数，提取出二进制最右侧的1
     *
     * @param num
     */
    public static int nearRightOne(int num) {
        return num & (~num + 1);
    }

    /**
     * 打印一个数的二进制形式
     *
     * @param num
     */
    public static void printByte(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num >> i) & 1);
        }
    }

    public static void main(String[] args) {
        int maxValue = 10000;
        int num = (int) (Math.random() * maxValue);
        printByte(num);
        System.out.println();
        printByte(nearRightOne(num));
    }
}
