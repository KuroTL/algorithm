package com.ixiaoyu2.primary.class21;

import sun.nio.ch.Net;

/**
 * @author :Administrator
 * @date :2022/5/14 0014
 */
public class Code02_IsRotation {


    /*
     * 判断str1和str2是否是旋转字符串
     * 例：
     * str1 :  abcdefg ——》旋转字符串：(1) 以0号字符旋转：abcdefg (2) 以1号字符旋转 bcdefga (3)以2号 字符旋转 cdefgab ……
     *         0123456
     * */

    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        String str = str1.concat(str1);

        return getIndexSubString(str, str2) != -1;
    }

    private static int getIndexSubString(String s1, String s2) {

        if (s1 == null || s2 == null || s1.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }


        int i1 = 0;
        int i2 = 0;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int[] next = getNextArray(chars2);

        while (i1 < chars1.length && i2 < chars2.length) {
            if (chars1[i1] == chars1[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }

        return i2 == chars2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[chars.length];

        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;

        while (i < next.length) {
            if (chars[i - 1] == chars[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "defgabc";
        System.out.println(isRotation(s1, s2));
    }

}
