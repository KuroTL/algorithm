package com.ixiaoyu2.primary.class03;

/**
 * @Author :Administrator
 * @Date :2022/3/4
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code03_StackAndQueryByDoubleListNode {


    static class DoubleListNode<T> {
        public T data;
        public DoubleListNode<T> prev;
        public DoubleListNode<T> next;

        public DoubleListNode(T data) {
            this.data = data;
        }
    }


    static class MyStack<T> {

        private DoubleListNode<T> head;

        public MyStack() {
        }
        //push

        public void push(T num) {
            if (head == null) {
                head = new DoubleListNode<>(num);
            } else {
                DoubleListNode next = new DoubleListNode<>(num);
                head.next = next;
                next.prev = head;
                head = next;
            }
        }

        //pop
        public T pop() {
            if (head == null) {
                return null;
            }
            T data = head.data;
            head = head.prev;
            return data;
        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    static class MyDeque<T> {

        DoubleListNode<T> head;
        DoubleListNode<T> end;

        public MyDeque() {
        }

        public void addFirst(T num) {
            DoubleListNode<T> cur = new DoubleListNode(num);
            if (head == null) {
                head = cur;
                end = cur;
            } else {
                cur.next = head;
                head.prev = cur;
                head = cur;
            }
        }

        public void addLast(T num) {
            DoubleListNode<T> cur = new DoubleListNode(num);
            if (head == null) {
                head = cur;
                end = head;
            } else {
                cur.prev = end;
                end.next = cur;
                end = cur;
            }
        }

        public T popFirst() {
            if (head == null) {
                return null;
            }

            T data = head.data;
            if (head == end) {
                head = null;
                end = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            return data;
        }

        public T popLast() {
            if (end == null) {
                return null;
            }
            T data = end.data;
            if (end == head) {
                end = null;
                head = null;
            } else {
                end = end.prev;
                end.next = null;
            }
            return data;
        }

        public boolean isEmpty() {
            return head == null && end == null;
        }
    }


    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
//        System.out.println(stack.isEmpty());
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        MyDeque<Integer> deque = new MyDeque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addLast(8);
        System.out.println(deque.isEmpty());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());

    }
}
