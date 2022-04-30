package com.ixiaoyu2.primary.class11;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Code07_PaperFolding {

    public static void printAllFolds(int n) {
        process(1, n, true);
    }

    private static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        process(i + 1, n, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, n, false);
    }

    public static void main(String[] args) {
        int n = 3;
        printAllFolds(n);
    }
}
