package com.ixiaoyu2.primary.class06;

import java.util.Comparator;

/**
 * @Author :Administrator
 * @Date :2022/3/12
 * @Description :com.msb.primary.class06
 * @Version: 1.0
 */
public class Code01_MyComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
