package com.ixiaoyu2.primary.class22;

/**
 * Manacher算法，返回给定字符串最长回文子串的长度
 *
 * @author :Administrator
 * @date :2022/5/15 0015
 */
public class Manacher {


    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 字符串转换："abcd" -> "#a#b#c#d#"
        char[] str = manacherString(s);

        // palindromeRadium[i] 是chars[i]为中点的回文半径
        int[] palindromeRadium = new int[str.length];

        // 最长回文子串中点下标
        int c = -1;

        // 最长回文子串最右边下标+1
        int r = -1;

        // 字符串最长回文子串的长度
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < str.length; i++) {
            /* 以i下标字符为中点的回文字符串的回文半径 至少为1
              (1)  l…(i`)…c…(i)…r  i<r，那么以i为中点的回文子串 回文半径 palindromeRadium[i]
                        i-c= c-i`-> i` = 2*c-i
                    ①  i 关于c 对称的下标 i` 的回文半径长度(回文范围下标：l+1 ~ i` ~ 2i`-l-1)  palindromeRadium[i`] > i`- l，palindromeRadium[i] 至少是r-i，还会不会更长需要后序判断
                    ②   palindromeRadium[i`] <= i`- l ,palindromeRadium[i] = palindromeRadium[i`]

             (2) i>=r，那么以i为中点的回文子串 回文半径 palindromeRadium[i] 至少为1
             */
            palindromeRadium[i] = r > i ? Math.min(palindromeRadium[2 * c - i], r - i) : 1;

            // 以i为中心的回文字符串最右边界R右边下标，(R+1)不越界 且 以i为中心的回文子串的左边界L左边下标，(L-1)不越界，即时回文字符串还可以向两边扩
            while (i + palindromeRadium[i] < str.length && i - palindromeRadium[i] > -1) {
                // 以i为中心的回文字符串最右边界 srt[R+1]==str[L-1]，则回文字符串的半径加1
                if (str[i + palindromeRadium[i]] == str[i - palindromeRadium[i]]) {
                    palindromeRadium[i]++;
                } else {
                    break;
                }
            }
            // 以i为中点的回文字符串的最右边下标 超过了r
            if (i + palindromeRadium[i] > r) {
                r = i + palindromeRadium[i];
                c = i;
            }
            // 以i为中点的回文字符串的长度为： 2*palindromeRadium[i] -1
            max = Math.max(max, (palindromeRadium[i] << 1) - 1);
        }
        // 返回原字符串的最大回文子字符串长度
        return max >> 1;
    }


    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] ans = new char[2 * chars.length + 1];
        int index = 0;
        for (int i = 0; i != ans.length; i++) {
            ans[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return ans;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
