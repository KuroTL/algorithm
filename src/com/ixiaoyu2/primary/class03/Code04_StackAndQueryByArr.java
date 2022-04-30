package com.ixiaoyu2.primary.class03;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code04_StackAndQueryByArr {

    /**
     * 数组实现栈
     */
    static class MyStack {
        private int[] arr;
        private static final int DEAUFAL_LENGTH = 16;
        private static int endIndex;
        private static int length;

        public MyStack() {
        }

        public void push(int num) {
            if (arr == null) {
                arr = new int[DEAUFAL_LENGTH];
                length = arr.length;
                endIndex = 0;
                arr[endIndex++] = num;
            } else {
                if (endIndex == length) {
                    length = length + (length >> 1);
                    int[] newArr = new int[length];
                    System.arraycopy(arr, 0, newArr, 0, arr.length);
                    arr = newArr;
                }
                arr[endIndex++] = num;
            }
        }

        public Integer pop() {
            if (arr == null && endIndex == 0) {
                return null;
            }
            return arr[--endIndex];
        }

        public boolean isEmpty() {
            return endIndex == 0;
        }
    }

    /**
     * 数组实现队列
     */
    static class MyQueue {

        private final int[] arr;
        private final int length;
        private int size;
        private int pushIndex;
        private int popIndex;

        private MyQueue(int length) {
            this.length = length;
            arr = new int[length];
            pushIndex = 0;
            popIndex = 0;
        }

        public static MyQueue getQueue(int length) {
            return new MyQueue(length);
        }

        public void push(int num) throws RuntimeException {
            if (size == length) {
                throw new RuntimeException("队列已满，添加失败");
            }
            arr[pushIndex] = num;
            size++;
            pushIndex = nextIndex(pushIndex);

        }

        public Integer pop() throws RuntimeException {
            if (size == 0) {
                return null;
            }

            int ans = arr[popIndex];
            popIndex = nextIndex(popIndex);
            size--;
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int index) {
            return index < length - 1 ? index + 1 : 0;
        }
    }


    public static void main(String[] args) {
//        MyStack stack = new MyStack();
//        System.out.println(stack.pop());
//        System.out.println(stack.isEmpty());
//        for (int i = 0; i < 24; i++) {
//            stack.push(i);
//        }
//        for (int i = 0; i < 16; i++) {
//            System.out.print(stack.pop() + " ");
//        }
//        System.out.println(stack.isEmpty());

        MyQueue queue = MyQueue.getQueue(10);
        System.out.println(queue.isEmpty());

        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(queue.pop() + " ");
        }
        System.out.println();
        for (int i = 10; i < 15; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.pop() + " ");
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.pop() + " ");
        }
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.pop() + " ");
        }


    }
}
