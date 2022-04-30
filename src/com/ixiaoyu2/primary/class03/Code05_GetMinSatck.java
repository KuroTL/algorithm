package com.ixiaoyu2.primary.class03;

import java.util.Stack;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code05_GetMinSatck {
        /*
        * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能

        1）pop、push、getMin操作的时间复杂度都是 O(1)。

        2）设计的栈类型可以使用现成的栈结构。
        */

    static class MyStack {
        private Stack<Integer> stack;
        private Stack<Integer> minValueStack;
        private int minValue;

        public MyStack() {
            stack = new Stack<>();
            minValueStack = new Stack<>();
        }

        public void push(int num) {
            if (minValueStack.isEmpty()) {
                minValue = num;
            } else {
                minValue = minValueStack.peek() < num ? minValueStack.peek() : num;
            }
            stack.push(num);
            minValueStack.push(minValue);
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                return null;
            }
            minValueStack.pop();
            return stack.pop();
        }

        public Integer getMin() {
            if (minValueStack.isEmpty()) {
                return null;
            }
            return minValueStack.peek();
        }
        public boolean isEmpty(){
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(4);
        stack.push(3);
        stack.push(4);
        while (!stack.isEmpty()){
            System.out.println(stack.getMin());
            System.out.println(stack.pop());
            System.out.println("===============");
        }
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
    }

}
