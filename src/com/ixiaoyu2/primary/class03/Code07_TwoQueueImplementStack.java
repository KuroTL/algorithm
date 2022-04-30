package com.ixiaoyu2.primary.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code07_TwoQueueImplementStack {

    static class MyStack {
        private Queue<Integer> pushQueue;
        private Queue<Integer> popQueue;

        public MyStack() {
            pushQueue = new LinkedList<>();
            popQueue = new LinkedList<>();
        }

        public void push(int num) {
            pushQueue.add(num);
        }

        public Integer pop() {
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.poll());
            }
            Integer ans = pushQueue.poll();
            Queue<Integer> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return ans;
        }

        public Integer peek() {
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.poll());
            }
            Integer ans = pushQueue.poll();
            popQueue.add(ans);
            Queue<Integer> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return ans;
        }

        public boolean isEmpty() {
            return pushQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        MyStack myStack = new MyStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("出错啦1~");
                    break;
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }

}

