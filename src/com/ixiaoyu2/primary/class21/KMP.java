package com.ixiaoyu2.primary.class21;

/**
 * KMP算法实现
 *
 * @author :Administrator
 * @date :2022/5/12 0012
 */
public class KMP {


    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }

        char[] sChars = s.toCharArray();
        char[] mChars = m.toCharArray();

        int sIndex = 0;
        int mIndex = 0;

        // next[i] i位置之前，0~i-1,前缀与后缀字符串相匹配的最大长度
        int[] next = getNextArray(mChars);
        while (sIndex < sChars.length && mIndex < mChars.length) {
            if (sChars[sIndex] == mChars[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (next[mIndex] == -1) {
                sIndex++;
            } else {
                mIndex = next[mIndex];
            }
        }
        return mIndex == mChars.length ? sIndex - mIndex : -1;
    }

    private static int[] getNextArray(char[] mChars) {
        if (mChars.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[mChars.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;

        while (pos < next.length) {
            // next[i] -> cn = next[i-1]  如果mChars[i-1] == mChars[cn] 则 next[i] = cn+1;
            if (mChars[pos - 1] == mChars[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                // 如果mChars[i-1] != mChars[cn]  cn = next[cn]
                cn = next[cn];
            } else {
                // 如果cn==0 表示来到mChar[0]!=mChars[pos-1] 则没有前缀和后缀相等的字符串
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "abcdabcdsssssss";
        String m = "bcds";
        System.out.println(getIndexOf(s, m));
    }
}
