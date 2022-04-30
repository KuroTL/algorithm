package com.ixiaoyu2.primary.class03;

import java.util.LinkedList;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code02_DeleteGivenValue {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int data) {
            this.data = data;
        }
    }

    /*把给定值都删除
     */
    public static ListNode deleteGivenValue(ListNode head, int value) {
        while (head != null && head.data == value) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null) {
            cur.next = null;
            if (next.data != value) {
                cur.next = next;
                cur = next;
            }
            next = next.next;
        }
        return head;
    }

    /*以下为对数器，用于测试*/

    public static ListNode comparator(ListNode head, int value) {
        if (head == null) {
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(Integer.valueOf(head.data));
            head = head.next;
        }
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            list.remove(Integer.valueOf(value));
        }
        if (list.size() == 0) {
            return null;
        }

        head = new ListNode(list.get(0));
        ListNode cur = head;
        for (int i = 1; i < list.size(); i++) {
            ListNode next = new ListNode(list.get(i));
            cur.next = next;
            cur = next;
        }
        return head;
    }

    public static ListNode generateRandomListNode(int maxLength, int maxValue, int value) {
        int length = (int) (Math.random() * (maxLength + 1));
        if (length == 0) {
            return null;
        }
        ListNode head = new ListNode((int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1));
        ListNode cur = head;
        for (int i = 0; i < length - 1; i++) {
            double random = Math.random();
            ListNode next = null;
            if (random < 0.5) {
                next = new ListNode((int) (Math.random() * maxValue + 1));
            } else {
                next = new ListNode(value);
            }
            cur.next = next;
            cur = next;
        }
        return head;
    }

    public static boolean check(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 != null ^ head2 != null) {
            return false;
        }

        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
            if (head1 != null ^ head2 != null) {
                return false;
            }
        }
        return true;
    }

    public static ListNode copyListNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode ans = new ListNode(head.data);
        ListNode cur = ans;
        while (head.next != null) {
            cur.next = new ListNode(head.next.data);
            cur = cur.next;
            head = head.next;
        }
        return ans;
    }

    public static void printListNode(ListNode head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 20;
        int value = 5;
        int testTimes = 1000000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            ListNode head = generateRandomListNode(maxLength, maxValue, value);
            ListNode head1 = copyListNode(head);
            ListNode head2 = copyListNode(head);

//            printListNode(head);
//            printListNode(head1);
//            printListNode(head2);


            ListNode ans1 = deleteGivenValue(head1, value);
            ListNode ans2 = comparator(head2, value);
            if (!check(ans1, ans2)) {
                System.out.println("算法出错~");
                printListNode(head);
                printListNode(ans1);
                printListNode(ans2);
                break;
            }
        }
        System.out.println("测试结束~");

    }
}
