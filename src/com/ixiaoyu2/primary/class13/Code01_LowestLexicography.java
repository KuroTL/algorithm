package com.ixiaoyu2.primary.class13;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author :Administrator
 * @date :2022/4/8 0008
 */
public class Code01_LowestLexicography {

    // 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，
    //返回所有可能的拼接结果中，字典序最小的结果

    public static String lowestLexicography1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return process(strs).first();
    }

    private static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs == null || strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            TreeSet<String> next = process(removOne(strs, i));
            for (String s1 : next) {
                s += s1;
                ans.add(s);
            }
        }
        return ans;
    }

    private static String[] removOne(String[] strs, int index) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        String[] ans = new String[strs.length - 1];
        int i = 0;
        for (int j = 0; j < strs.length; j++) {
            if (j != index) {
                ans[i++] = strs[j];
            }
        }
        return ans;
    }


    // 贪心算法

    public static String lowestLexicography2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicography2(arr1).equals(lowestLexicography1(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
