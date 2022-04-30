package com.ixiaoyu2.primary.code17;

/**
 * @author :Administrator
 * @date :2022/4/25 0025
 */
public class Code07_LongestPalindromicSubString {

    //给定一个字符串str，返回这个字符串的最长回文子序列长度
    //比如 ： str = “a12b3c43def2ghi1kpm”
    //最长回文子序列是“1234321”或者“123c321”，返回长度7


    // 算法1：获取str倒序字符串成str2，求str和str2的最长子序列

    public static int longestPalindromicSubString(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }

        char[] chars = string.toCharArray();

        char[] reverseChar = new char[chars.length];

        int n = chars.length;
        for (int j = n - 1; j >= 0; j--) {
            reverseChar[n - 1 - j] = chars[j];
        }

        int[][] dp = new int[n][n];

        dp[0][0] = chars[0] == reverseChar[0] ? 1 : 0;

        // int[i][0]
        for (int i = 1; i < n; i++) {
            dp[i][0] = chars[i] == reverseChar[0] ? 1 : dp[i - 1][0];
        }
        // int[0][j]
        for (int j = 1; j < n; j++) {
            dp[0][j] = chars[0] == reverseChar[j] ? 1 : dp[0][j - 1];
        }
        // 一般位置的 int[i][j]
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = chars[i] == chars[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[n - 1][n - 1];
    }


    // 算法2  样本对于模型

    public static int longestPalindromicSubString2(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        char[] chars = string.toCharArray();
        return process(chars, 0, chars.length - 1);
    }


    private static int process(char[] chars, int i, int j) {
        // 1个字符
        if (i == j) {
            return 1;
        }
        // 2个字符
        if (i == j - 1) {
            return chars[i] == chars[j] ? 2 : 0;
        }
        // 其他情况
        // 回文以i开头，不以j结尾
        int p1 = process(chars, i, j - 1);
        // 回文不以i开头，以j结尾
        int p2 = process(chars, i + 1, j);
        // 回文必须以i开头,j结尾
        int p3 = chars[i] == chars[j] ? 2 + process(chars, i + 1, j - 1) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    // 以上暴力递归直接改动态规划表

    public static int longestPalindromicSubString3(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }

        char[] chars = string.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];

        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            // base case dp[i][j]  i==j时  dp[i][j]=1;
            dp[i][i] = 1;
            // base case dp[i][j]  i==j-1时 chars[i]==char[j]?2:0;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 0;
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i + 1][j];
                int p3 = chars[i] == chars[j] ? 2 + dp[i + 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][n - 1];
    }


    // 对位置进行优化

    public static int longestPalindromicSubString4(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }

        char[] chars = string.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];

        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            // base case dp[i][j]  i==j时  dp[i][j]=1;
            dp[i][i] = 1;
            // base case dp[i][j]  i==j-1时 chars[i]==char[j]?2:0;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 0;
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (chars[i] == chars[j]) {
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static String generateString(int maxLength) {
        int l = (int) (Math.random() * maxLength);
        char[] chars = new char[l];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) (Math.random() * 26) + 'a');
        }
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
//        String s = "abcba";
//        System.out.println(longestPalindromicSubString(s));
//        System.out.println(longestPalindromicSubString2(s));
//        System.out.println(longestPalindromicSubString3(s));
//        System.out.println(longestPalindromicSubString4(s));


        int maxLength = 100;
        int textTimes = 1000000;
        for (int i = 0; i < textTimes; i++) {
            String str = generateString(maxLength);
            int ans1 = longestPalindromicSubString(str);
            int ans2 = longestPalindromicSubString2(str);
            int ans3 = longestPalindromicSubString3(str);
            int ans4 = longestPalindromicSubString4(str);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("出错");
                System.out.println("str:" + str);
                System.out.println("ans1" + ans1);
                System.out.println("ans2" + ans2);
                System.out.println("ans3" + ans3);
                System.out.println("ans4" + ans4);
                break;
            }
        }
        System.out.println("结束");

    }
}
