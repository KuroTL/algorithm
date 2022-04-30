package com.ixiaoyu2.primary.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Administrator
 * @Date :2022/3/4
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Code01_ReverseList {

    /**
     * 单链表结构，数据存放int类型
     */
    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 双链表结构，数据存放int类型
     */
    static class DoubleListNode {
        int data;
        DoubleListNode prev;
        DoubleListNode next;

        public DoubleListNode(int data, DoubleListNode prev, DoubleListNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /*单链表反转*/

    public static ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /*以下代码为对数器，测试单链表反转*/

    public static ListNode comparator(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        for (int i = 1; i < list.size(); i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(list.size() - 1);
    }


    public static ListNode generateRandomListNode(int listLength, int maxValue) {
        ListNode cur = new ListNode(((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue)), null);
        //保证生成的链表至少有1个节点
        ListNode head = cur;
        int length = (int) (Math.random() * listLength + 1);
        for (int i = 0; i < length - 1; i++) {
            cur.next = new ListNode(((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue)), null);
            cur = cur.next;
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

    public static ListNode copyListNode(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode ans = new ListNode(head.data, null);
        ListNode cur = ans;
        while (head.next != null) {
            cur.next = new ListNode(head.next.data, null);
            cur = cur.next;
            head = head.next;
        }
        return ans;
    }
    /*双链表反转*/

    public static DoubleListNode reverseDoubleListNode(DoubleListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleListNode pre = null;
        DoubleListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /*以下代码为对数器，用于测试*/

    public static DoubleListNode comparator(DoubleListNode head) {
        if (head == null || (head.next == null && head.prev == null)) {
            return head;
        }
        List<DoubleListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        list.get(0).prev = list.get(1);
        for (int i = 1; i < list.size(); i++) {
            list.get(i).next = list.get(i - 1);
            if (i == list.size() - 1) {
                list.get(i).prev = null;
            } else {
                list.get(i).prev = list.get(i + 1);
            }
        }
        return list.get(list.size() - 1);
    }

    public static DoubleListNode generateRandomDoubleListNode(int maxLength, int maxValue) {
        //保证至少有一个节点
        int length = (int) (Math.random() * maxLength + 1);
        DoubleListNode head = new DoubleListNode((int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1), null, null);
        DoubleListNode cur = head;

        for (int i = 0; i < length - 1; i++) {
            DoubleListNode next = new DoubleListNode((int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1), null, null);
            cur.next = next;
            next.prev = cur;
            cur = next;
        }
        return head;
    }

    public static DoubleListNode copyDoubleListNode(DoubleListNode head) {
        if (head == null) {
            return null;
        }
        DoubleListNode ans = new DoubleListNode(head.data, null, null);
        DoubleListNode cur = ans;
        while (head.next != null) {
            DoubleListNode next = new DoubleListNode(head.next.data, null, null);
            cur.next = next;
            next.prev = cur;
            cur = next;
            head = head.next;
        }
        return ans;
    }

    public static boolean check(DoubleListNode head1, DoubleListNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 != null ^ head2 != null) {
            return false;
        }

        DoubleListNode end1 = null;
        DoubleListNode end2 = null;
        //正向比较
        while (head1 != null && head2 != null) {
            if (head1.data != head1.data) {
                return false;
            }
            end1 = head1;
            end2 = head2;
            head1 = head1.next;
            head2 = head2.next;
            if (head1 != null ^ head2 != null) {
                return false;
            }
        }
        //逆向比较
        while (end1 != null && end2 != null) {
            if (end1.data != end1.data) {
                return false;
            }

            end1 = end1.prev;
            end2 = end2.prev;
            if (end1 != null ^ end2 != null) {
                return false;
            }
        }
        return true;
    }

    public static void printDoubleListNode(DoubleListNode head) {
        if (head == null) {
            return;
        }
        DoubleListNode end = null;
        while (head != null) {
            System.out.print(head.data+" ");
            end = head;
            head = head.next;
        }
        System.out.println();
        while (end != null) {
            System.out.print(end.data+" ");
            end = end.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*测试单链表反转*/

        int listLength = 20;
        int maxValue = 10;
        int testTimes = 1000000;
        System.out.println("测试开始~");
//
//        ListNode head01 = generateRandomListNode(listLength, maxValue);
//        ListNode head02 = copyListNode(head01);
////        printListNode(head01);
////        printListNode(head02);


        DoubleListNode head01 = generateRandomDoubleListNode(listLength, maxValue);
        DoubleListNode head02 = copyDoubleListNode(head01);
        DoubleListNode head03 = copyDoubleListNode(head01);

        printDoubleListNode(head01);
        System.out.println("_______________________________");
        printDoubleListNode(head02);
        System.out.println("_______________________________");
        DoubleListNode ans1 = reverseDoubleListNode(head02);
        DoubleListNode ans2 = comparator(head03);

        printDoubleListNode(ans1);
        System.out.println("_______________________________");
        printDoubleListNode(ans2);

//        for (int i = 0; i < testTimes; i++) {

//            {//这段代码测试单链表反转
//                ListNode head01 = generateRandomListNode(listLength, maxValue);
//                ListNode head02 = copyListNode(head01);
//                ListNode head03 = copyListNode(head01);
//                ListNode ans1 = reverseListNode(head02);
//                ListNode ans2 = comparator(head03);
//                if (!check(ans1, ans2)) {
//                    System.out.println("算法出错了~");
//
//                    System.out.print("初始单链表");
//                    printListNode(head01);
//
//                    System.out.print("待测算法单链表");
//                    printListNode(ans1);
//
//                    System.out.print("对数器反转单链表");
//                    printListNode(ans2);
//                    break;
//                }
//            }
//
//            {
//                DoubleListNode head01 = generateRandomDoubleListNode(listLength, maxValue);
//                DoubleListNode head02 = copyDoubleListNode(head01);
//                DoubleListNode head03 = copyDoubleListNode(head01);
//                DoubleListNode ans1 = reverseDoubleListNode(head02);
//                DoubleListNode ans2 = comparator(head03);
//                if (!check(ans1, ans2)) {
//                    System.out.println("算法出错了~");
//
//                    System.out.print("初始单链表");
//                    printDoubleListNode(head01);
//
//                    System.out.print("待测算法单链表");
//                    printDoubleListNode(ans1);
//
//                    System.out.print("对数器反转单链表");
//                    printDoubleListNode(ans2);
//                    break;
//                }
//
//
//            }
//        }
        System.out.println("测试结束~");
    }

}
