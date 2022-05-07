package com.ixiaoyu2.primary.class17;

/**
 * @author :Administrator
 * @date :2022/4/23 0023
 */
public class Code04_ConvertToLetterString {

    //规定1和A对应、2和B对应、3和C对应...26和Z对应，那么一个数字字符串比如"111”就可以转化为:"AAA"、"KA"和"AK"。
    // 给定一个只有数字字符组成的字符串str，返回有多少种转化结果

    public static int convertToLetterSting(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    /**
     * 从i位置开始，可以转换的最多情况
     *
     * @param chars 字符串数组
     * @param i     下标i
     * @return 可以转换的次数
     */
    private static int process(char[] chars, int i) {
        // 找到1种转换方法
        if (i == chars.length) {
            return 1;
        }
        // 当前位置是0，无法直接转换，找到0种结果
        if (chars[i] == '0') {
            return 0;
        }
        // 后面位置有几种转换方法
        int way = process(chars, i + 1);
        // 当前位置和后一个位置组合起来的转换方法
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
            way += process(chars, i + 2);
        }
        return way;
    }

    //  优化 动态规划

    public static int convertToLetterSting2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] map = new int[n + 1];
        map[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                int way = map[i + 1];
                if (i + 1 < n && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
                    way += map[i + 2];
                }
                map[i] = way;
            }
        }
        return map[0];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n);
            String s = randomString(len);
            int ans0 = convertToLetterSting(s);
            int ans1 = convertToLetterSting2(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
