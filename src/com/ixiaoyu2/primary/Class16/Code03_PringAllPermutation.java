package com.ixiaoyu2.primary.Class16;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/4/18 0018
 */
public class Code03_PringAllPermutation {

    // 返回一个字符串的全排列

    public static List<String> allPermutation(String string) {
        List<String> ans = new ArrayList<>();
        if (string == null || string.length() == 0) {
            return ans;
        }
        char[] str = string.toCharArray();
        List<Character> chars = new ArrayList<>();
        for (char c : str) {
            chars.add(c);
        }
        process(chars, ans, "");
        return ans;
    }

    private static void process(List<Character> chars, List<String> ans, String curSubString) {
        if (chars.isEmpty()) {
            ans.add(curSubString);
        } else {
            for (int i = 0; i < chars.size(); i++) {
                char cur = chars.get(i);
                chars.remove(i);
                process(chars, ans, curSubString.concat(String.valueOf(cur)));
                chars.add(i, cur);
            }
        }
    }

    public static List<String> allPermutation2(String string) {
        List<String> ans = new ArrayList<>();
        if (string == null || string.length() == 0) {
            return ans;
        }
        char[] chars = string.toCharArray();

        process2(chars, 0, ans);
        return ans;
    }

    private static void process2(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, i, index);
                process2(chars, index + 1, ans);
                swap(chars, i, index);
            }
        }

    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    //  去重的全排列

    public static List<String> allPermutation3(String string) {
        List<String> ans = new ArrayList<>();
        if (string == null || string.length() == 0) {
            return ans;
        }
        char[] chars = string.toCharArray();
        process3(chars, 0, ans);
        return ans;
    }

    private static void process3(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                // 剪枝 对于重复出现的字符，直接跳过
                if (!visited[chars[i]]) {
                    visited[chars[i]] = true;
                    swap(chars, i, index);
                    process3(chars, index + 1, ans);
                    swap(chars, i, index);
                }

            }
        }

    }

    public static void main(String[] args) {
        String s = "113";
        allPermutation(s).forEach(System.out::println);
        System.out.println("=================");
        allPermutation2(s).forEach(System.out::println);
        System.out.println("=================");
        allPermutation3(s).forEach(System.out::println);

    }

}
