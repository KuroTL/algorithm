package com.ixiaoyu2.rookie.class06;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class MyComparator implements Comparator<Integer> {

    /**
     * 返回负数，先排o1，返回正数，先排o2，返回0，随意
     * @param o1 第一个数
     * @param o2 第2个数
     * @return 返回比较结果
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}

class Test01 {

    public static void main(String[] args) {
        Integer[] arr = {3, 2, 5, 9, 7, 4, 0, 2, 3, 5, 7, 1, 11, 2, 5, 3, 9, 8, 5, 2, 4, 7, 6, 3, 2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr,new MyComparator());
        System.out.println(Arrays.toString(arr));
    }
}
