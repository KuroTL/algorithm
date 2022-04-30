package com.ixiaoyu2.primary.class13;

import java.util.HashSet;

/**
 * @author :Administrator
 * @date :2022/4/9 0009
 */
public class Code02_LeastLight {

 /*   给定一个字符串str，只由‘X’和‘.’两种字符构成。
    ‘X’表示墙，不能放灯，也不需要点亮
    ‘.’表示居民点，可以放灯，需要点亮
    如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
    返回如果点亮str中所有需要点亮的位置，至少需要几盏灯*/

    public static int leastLight(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int ans = 0;
        int i = 0;
        while (i < chars.length) {
            if ('x' == chars[i]) {
                i++;
            } else {
                ans++;
                if (i + 1 == chars.length) {
                    break;
                } else {
                    if ('x' == chars[i + 1]) {
                        i += 2;
                    } else {
                        i += 3;
                    }
                }
            }
        }
        return ans;
    }


    public static int leastLight2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        char[] chars = str.toCharArray();
        return process(chars, 0, hashSet);
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯

    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if ('x' != str[i]) {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // index不放灯
            int no = process(str, index + 1, lights);
            // index 放灯
            // 如果index位置是'x'，那么yes=Integer.MAX_VALUE;
            int yes = Integer.MAX_VALUE;
            // index位置是‘.'
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }


    public static String randomString(int length) {
        char[] chars = new char[(int) (Math.random() * length + 1)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Math.random() < 0.5 ? 'x' : '.';
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = leastLight(test);
            int ans2 = leastLight2(test);
            if (ans1 != ans2) {
                System.out.println("出错啦!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(test);
                break;
            }
        }
        System.out.println("finish!");
    }
}
