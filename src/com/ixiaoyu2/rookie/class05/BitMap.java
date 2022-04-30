package com.ixiaoyu2.rookie.class05;

import java.util.HashSet;

/**
 * @Author :Administrator
 * @Date :2022/2/23
 * @Description :com.msb.rookie.class05
 * @Version: 1.0
 */
public class BitMap {
    private long[] bits;

    public BitMap(int max) {
        bits = new long[(max + 64) >> 6];
    }

    public void add(int val) {
        if (((val + 64) >> 6) > bits.length) {
            bits = new long[(val + 64) >> 6];
        }
        bits[val >> 6] |= (1L << (val & 63));
    }

    public void delete(int val) {
        if (((val + 64) >> 6) <= bits.length) {
            bits[val >> 6] &= ~(1L << (val & 63));
        }
    }

    public boolean contains(int val) {
        if (((val + 64) >> 6) <= bits.length) {
            return (bits[val >> 6] & (1L << (val & 63))) != 0;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops1!");
                    System.out.println(num);
                    System.out.println("bitmap 有：" + bitMap.contains(num));
                    System.out.println("hsahset 有" + set.contains(num));
                    System.out.println("---------");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops2!");
                System.out.println(num);
                System.out.println("bitmap 有：" + bitMap.contains(num));
                System.out.println("hsahset 有" + set.contains(num));
                System.out.println("---------");
            }
        }
        System.out.println("测试结束！");
    }

}
