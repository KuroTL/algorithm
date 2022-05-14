package com.ixiaoyu2.primary.class22;

/**
 * @author :Administrator
 * @date :2022/5/15 0015
 */
public class Code01_AddAndShortEnd {

    //给一个字符串str，最少拼接一个字符串s，使得str是回文字符串，返回s

    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] chars = manacherString(s);

        int[] pArr = new int[chars.length];
        int r = -1;
        int c = -1;
        int i = 0;
        for (; i < chars.length; i++) {

            pArr[i] = r > i ? Math.min(r - i, pArr[2 * c - i]) : 1;

            while (i + pArr[i] < chars.length && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            if (r == chars.length) {
                break;
            }
        }

        i = i - pArr[i];

        char[] res = new char[(i + 1) >> 1];
        int index = 0;
        for (; i >= 0; i -= 2) {
            res[index++] = chars[i];
        }
        return String.valueOf(res);

    }


    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[2 * chars.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }


    public static void main(String[] args) {
        String str1 = "qqqabcdd123sfeqsdaf321";
        System.out.println(shortestEnd(str1));
    }
}
