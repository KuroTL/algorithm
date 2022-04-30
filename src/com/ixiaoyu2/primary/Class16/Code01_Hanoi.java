package com.ixiaoyu2.primary.Class16;

/**
 * @author :Administrator
 * @date :2022/4/18 0018
 */
public class Code01_Hanoi {

    //汉诺塔问题，打印步骤  n为层数

    public static void hanoi(int n) {
        fromLeftToRight(n);
        System.out.println("===================");
        process(n, "left", "right", "middle");
    }

    private static void fromLeftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        fromLeftToMiddle(n - 1);
        System.out.println("move " + n + " from left to right");
        fromMiddleToRight(n - 1);
    }

    private static void fromMiddleToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from middle to right");
            return;
        }
        fromMiddleToLeft(n - 1);
        System.out.println("move " + n + " from middle to right");
        fromLeftToRight(n - 1);
    }

    private static void fromMiddleToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from middle to left");
            return;
        }
        fromMiddleToRight(n - 1);
        System.out.println("move " + n + " from middle to left");
        fromRightToLeft(n - 1);
    }

    private static void fromLeftToMiddle(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to middle");
            return;
        }
        fromLeftToRight(n - 1);
        System.out.println("move " + n + " from left to middle");
        fromRightToMiddle(n - 1);

    }

    private static void fromRightToMiddle(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to middle");
            return;
        }
        fromRightToLeft(n - 1);
        System.out.println("move " + n + " from right to middle");
        fromLeftToMiddle(n - 1);

    }

    private static void fromRightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        fromRightToMiddle(n - 1);
        System.out.println("move " + n + " from right to left");
        fromMiddleToLeft(n - 1);
    }
    // 将以上递归进行抽象

    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move " + n + " from " + from + " to " + to);
            return;
        }
        process(n - 1, from, other, to);
        System.out.println("move " + n + " from " + from + " to " + to);
        process(n - 1, other, to, from);
    }


    public static void main(String[] args) {
        hanoi(3);
    }
}
