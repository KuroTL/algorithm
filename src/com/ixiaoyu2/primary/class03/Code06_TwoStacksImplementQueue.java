package com.ixiaoyu2.primary.class03;

import java.util.Stack;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code06_TwoStacksImplementQueue {

    //如何用栈结构实现队列结构
    static class MyQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public MyQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void add(int num) {
            pushStack.push(num);
        }

        public Integer poll() {
            if (popStack.empty() && pushStack.empty()) {
                return null;
            }
            pushStackPollToPopStack();
            return popStack.pop();
        }

        public Integer peek() {
            if (popStack.empty() && pushStack.empty()) {
                return null;
            }
            pushStackPollToPopStack();
            return popStack.peek();
        }

        public boolean isEmpty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }

        private void pushStackPollToPopStack() {
            if (popStack.empty()) {
                while (!pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();


        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println("___________");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("___________");
        System.out.println(queue.peek());
        System.out.println("___________");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }
}
