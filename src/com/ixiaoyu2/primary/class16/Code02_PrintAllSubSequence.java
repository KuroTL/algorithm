package com.ixiaoyu2.primary.class16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/4/18 0018
 */
public class Code02_PrintAllSubSequence {

    // 打印一个字符串的全部子序列

    public static List<String> subSequence(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> subString = new ArrayList<>();
        char[] chars = str.toCharArray();
        process(chars, 0, subString, "");
        return subString;
    }

    private static void process(char[] chars, int index, List<String> ans, String curSubString) {
        if (index == chars.length) {
            ans.add(curSubString);
            return;
        }
        // 没有要当前位置的字符
        process(chars, index + 1, ans, curSubString);
        // 要了当前位置的字符
        process(chars, index + 1, ans, curSubString.concat(String.valueOf(chars[index])));
    }

    // 不要重复的子序列

    public static List<String> subSequenceNoRepeat(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        HashSet<String> subString = new HashSet<>();
        char[] chars = str.toCharArray();
        process2(chars, 0, subString, "");
        return new ArrayList<>(subString);
    }

    private static void process2(char[] chars, int index, HashSet<String> ans, String curSubString) {
        if (index == chars.length) {
            ans.add(curSubString);
            return;
        }
        process2(chars, index + 1, ans, curSubString);
        process2(chars, index + 1, ans, curSubString.concat(String.valueOf(chars[index])));
    }


    public static void main(String[] args) {
        String test = "aas";
        List<String> ans1 = subSequence(test);
        List<String> ans2 = subSequenceNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
