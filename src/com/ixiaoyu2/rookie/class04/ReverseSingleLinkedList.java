package com.ixiaoyu2.rookie.class04;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Administrator
 * @Date :2022/2/20
 * @Description :com.msb.rookie.class04
 * @Version: 1.0
 */
public class ReverseSingleLinkedList {


    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class DoubleNode<E> {
        E data;
        DoubleNode<E> prev;
        DoubleNode<E> next;

        public DoubleNode(E data, DoubleNode<E> prev, DoubleNode<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 100;
        int testTimes = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
//            Node<Integer> node1 = generateLinkedList(maxLength, maxValue);
//            List<Integer> origin1 = getLinkedListOriginOrder(node1);
//            node1 = reverseLinkedList(node1);
//            if (!checkLinkedListReverse(origin1, node1)) {
//                System.out.println("Oops1!");
//            }
//
//            Node<Integer> node2 = generateLinkedList(maxLength, maxValue);
//            List<Integer> origin2 = getLinkedListOriginOrder(node2);
//            node2 = testReverseLinkedList(node2);
//            if (!checkLinkedListReverse(origin2, node2)) {
//                System.out.println("Oops2!");
//            }

            DoubleNode<Integer> node3 = generateDoubleLinkedList(maxLength, maxValue);
            List<Integer> origin3 = getDoubleLinkedListOriginOrder(node3);
            DoubleNode<Integer> node4 = reverseDoubleLinkedList(node3);
            if (!checkDoubleLinkedListReverse(origin3, node4)) {
                printLinkedList(node3);
                printLinkedList(node4);
                System.out.println("Oops3!");
            }

            DoubleNode<Integer> node5 = generateDoubleLinkedList(maxLength, maxValue);

            List<Integer> origin4 = getDoubleLinkedListOriginOrder(node4);
            DoubleNode<Integer> node6 = testReverseDoubleLinkedList(node4);
            if (!checkDoubleLinkedListReverse(origin4, node6)) {
                System.out.println("Oops4!");
                printLinkedList(node5);
                printLinkedList(node6);
                break;
            }
        }
        System.out.println("test finish!");
    }
    //给定一个单链表的头head，完成链表的逆序调整

    public static <E> Node<E> reverseLinkedList(Node<E> head) {
        if (null == head) {
            return null;
        }
        Node<E> pre = null;
        Node<E> next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //给定一个双链表的头head，完成链表的逆序调整

    public static <E> DoubleNode<E> reverseDoubleLinkedList(DoubleNode<E> head) {
        if (head == null) {
            return null;
        }
        DoubleNode<E> pre = null;
        DoubleNode<E> next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static Node<Integer> generateLinkedList(int maxLength, int MaxValue) {
        int size = (int) (Math.random() * (maxLength + 1));
        if (size == 0) {
            return null;
        }
        Node<Integer> head = new Node<>((int) (Math.random() * MaxValue), null);
        Node<Integer> cur = head;
        for (int i = 1; i < size; i++) {
            Node<Integer> node = new Node<>((int) (Math.random() * MaxValue), null);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static DoubleNode<Integer> generateDoubleLinkedList(int maxLength, int MaxValue) {
        int size = (int) (Math.random() * (maxLength + 1));
        if (size == 0) {
            return null;
        }
        DoubleNode<Integer> head = new DoubleNode<>((int) (Math.random() * MaxValue), null, null);
        DoubleNode<Integer> cur = head;
        for (int i = 1; i < size; i++) {
            DoubleNode<Integer> node = new DoubleNode<>((int) (Math.random() * MaxValue), cur, null);
            cur.next = node;
            node.prev = cur;
            cur = node;
        }
        return head;
    }


    public static <E> void printLinkedList(Node<E> head) {
        while (null != head) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static <E> void printLinkedList(DoubleNode<E> head) {
        DoubleNode<E> end =null;
        while (null != head) {
            System.out.print(head.data + " ");
            end = head;
            head = head.next;
        }
        System.out.println();
        while (null != end) {
            System.out.print(end.data + " ");
            end = end.prev;
        }
    }

    //借助ArrayList实现单链表的反转

    public static <E> Node<E> testReverseLinkedList(Node<E> head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node<E>> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int size = list.size();
        for (int i = 1; i < size; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(size - 1);

    }

    public static <E> DoubleNode<E> testReverseDoubleLinkedList(DoubleNode<E> head) {
        if (head == null) {
            return null;
        }
        //将链表节点添加到ArrayList集合中
        ArrayList<DoubleNode<E>> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int size = list.size();

        list.get(0).next = null;
        DoubleNode<E> pre = list.get(0);
        DoubleNode<E> cur = null;
        for (int i = 1; i < size; i++) {
            cur = list.get(i);
            cur.prev = null;
            cur.next = pre;
            pre.prev = cur;
            pre = cur;
        }
        return list.get(size - 1);

    }

    public static <E> List<E> getLinkedListOriginOrder(Node<E> head) {
        List<E> origin = new ArrayList<>();
        while (head != null) {
            origin.add(head.data);
            head = head.next;
        }
        return origin;
    }

    public static <E> boolean checkLinkedListReverse(List<E> origin, Node<E> head) {
        int size = origin.size();
        for (int i = size - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.data)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static <E> List<E> getDoubleLinkedListOriginOrder(DoubleNode<E> head) {
        List<E> origin = new ArrayList<>();
        while (head != null) {
            origin.add(head.data);
            head = head.next;
        }
        return origin;
    }

    public static <E> boolean checkDoubleLinkedListReverse(List<E> origin, DoubleNode<E> head) {
        int size = origin.size();
        DoubleNode<E> end = null;
        for (int i = size - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.data)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < size; i++) {
            if (!origin.get(i).equals(end.data)) {
                return false;
            }
            end = end.prev;
        }
        return true;
    }

}
