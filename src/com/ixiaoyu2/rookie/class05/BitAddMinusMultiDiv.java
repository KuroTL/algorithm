package com.ixiaoyu2.rookie.class05;

/**
 * @Author :Administrator
 * @Date :2022/2/23
 * @Description :com.msb.rookie.class05
 * @Version: 1.0
 */
public class BitAddMinusMultiDiv {


    /**
     * 用位运算实现加法
     *
     * @param num1 被加数num
     * @param num2 加数num
     * @return 返回两个加数的和
     */
    public int add(int num1, int num2) {
        int sum = num1;
        while (num2 != 0) {
            sum = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = sum;
        }
        return sum;
    }

    /**
     * 用位运算实现2个数相减
     *
     * @param num1 被减数
     * @param num2 减数
     * @return 差
     */
    public int minus(int num1, int num2) {
        return add(num1, add(~num2, 1));
    }

    /**
     * 用位运算实现乘法
     *
     * @param num1 被乘数
     * @param num2 乘数
     * @return 乘积
     */
    public int multi(int num1, int num2) {
        int res = 0;
        while (num2 != 0) {
            if ((num2 & 1) != 0) {
                res = add(num1, res);
            }
            num1 = num1 << 1;
            num2 = num2 >>> 1;
        }
        return res;
    }

    /**
     * 用位运算实现除法
     *
     * @param num1 被除数
     * @param num2 除数
     * @return 商
     */
    public int div(int num1, int num2) {
        //当被除数、除数都为系统最小时，返回1
        if (num1 == Integer.MIN_VALUE && num2 == Integer.MIN_VALUE) {
            return 1;
            //当被除数不为系统最小，除数为系统最小时，返回0
        } else if (num2 == Integer.MIN_VALUE) {
            return 0;
        } else if (num1 == Integer.MIN_VALUE) {
            //当被除数为系统最小，除数为-1时，返回系统最大（理论为系统最大+1,
            // 但是没有系统最大+1的数，约定返回系统最大
            if (num2 == add(~1, 1)) {
                return Integer.MAX_VALUE;
            } else {
                int temp = division(add(num1, 1), num2);
                return add(temp, division(minus(num1, multi(temp, num2)), num2));
            }
        } else {
            return division(num1, num2);
        }
    }

    /**
     * 当被除数、除数不为系统最小时
     *
     * @param num1 被除数
     * @param num2 除数
     * @return 商
     */
    private int division(int num1, int num2) {
        int x = isNeg(num1) ? add(~num1, 1) : num1;
        int y = isNeg(num2) ? add(~num2, 1) : num2;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(num1) ^ isNeg(num2) ? add(~res, 1) : res;
    }

    /**
     * 判断是否为负
     *
     * @param num 整数
     * @return 为负返回true
     */
    private boolean isNeg(int num) {
        return num < 0;
    }
}

class Test01 {
    public static void main(String[] args) {
        BitAddMinusMultiDiv test = new BitAddMinusMultiDiv();
        System.out.println(test.add(2, 2));
        System.out.println(test.minus(4, 1));
        System.out.println(test.multi(5, 5));
        System.out.println(test.div(9, 2));
    }


}
