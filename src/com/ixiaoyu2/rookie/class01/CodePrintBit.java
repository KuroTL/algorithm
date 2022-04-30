package com.ixiaoyu2.rookie.class01;

/**
 * @Author :Administrator
 * @Date :2022/2/16
 * @Description :com.msb.rookie.class01
 * @Version: 1.0
 */
public class CodePrintBit {
    public static void main(String[] args) {
//        int num = Integer.MAX_VALUE;
        int num = 1;
        int num1 = -num;
        printBit(num);
        printBit(num1);
    }
    /**
     * 打印整数的位信息，int 4字节长度，32位，打印每位的信息
     *
     * @param num 传入的整数
     */
    public static void printBit(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & 1 << i) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
