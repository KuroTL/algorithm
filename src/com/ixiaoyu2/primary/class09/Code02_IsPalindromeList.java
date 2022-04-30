package com.ixiaoyu2.primary.class09;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/3/21 0021
 */
public class Code02_IsPalindromeList {
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isPalindromeList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 至少2个节点
        Node n1 = head;
        Node n2 = head;
        // n1 ，链表长度为奇数时，为中点；链表长度为偶数时，为上中点
        while (n2.next != null && n2.next.next != null) {
            n2 = n2.next.next;
            n1 = n1.next;
        }

        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        // n1 是最后一个节点，n3 是最后一个节点
        n3 = n1;
        n2 = head;

        boolean ans = true;

        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                ans = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // n1 为倒数第2个节点
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return ans;
    }

    // need n extra space 使用List
    public static boolean isPalindromeList2(Node head) {
        if (head == null | head.next == null) {
            return true;
        }

        List<Node> list = new ArrayList<>();
        Node cur = head;

        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        cur = head;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // need n extra space 使用栈
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindromeList(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
