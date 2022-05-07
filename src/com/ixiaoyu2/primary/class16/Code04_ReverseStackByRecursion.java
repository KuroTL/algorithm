package com.ixiaoyu2.primary.class16;

import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/4/18 0018
 */
public class Code04_ReverseStackByRecursion {

    //给你一个栈，请你逆序这个栈，
    //不能申请额外的数据结构，
    //只能使用递归函数。 如何实现?

    public static <T> void reverseStack(Stack<T> stack) {
        if (stack == null || stack.empty()) {
            return;
        }
        T last = precess(stack);
        reverseStack(stack);
        stack.push(last);
    }

    private static <T> T precess(Stack<T> stack) {
        T cur = stack.pop();
        if (stack.empty()) {
            return cur;
        } else {
            T last = precess(stack);
            stack.push(cur);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverseStack(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
