package com.ixiaoyu2.primary.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/3/21 0021
 */
public class Code01_linkedListMid {


    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 链表至少有3个节点
        Node sPoint = head.next;
        Node qPoint = head.next.next;

        while (qPoint.next != null && qPoint.next.next != null) {
            qPoint = qPoint.next.next;
            sPoint = sPoint.next;
        }
        return sPoint;
    }

    public static Node test1(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 1) >> 1);
    }


    //2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //链表至少有2个节点
        Node sPoint = head.next;
        Node qPoint = head.next;

        while (qPoint.next != null && qPoint.next.next != null) {
            qPoint = qPoint.next.next;
            sPoint = sPoint.next;
        }
        return sPoint.next;
    }

    public static Node test2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get(list.size() >> 1);
    }

    //3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 至少3个节点
        //链表至少有2个节点
        Node sPoint = head;
        Node qPoint = head.next.next;
        while (qPoint.next != null && qPoint.next.next != null) {
            qPoint = qPoint.next.next;
            sPoint = sPoint.next;
        }
        return sPoint;
    }

    public static Node test3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 3) >> 1);
    }

    //4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        // 至少3个节点
        Node sPoint = head;
        Node qPoint = head.next;
        while (qPoint.next != null && qPoint.next.next != null) {
            qPoint = qPoint.next.next;
            sPoint = sPoint.next;
        }
        return sPoint;
    }

    public static Node test4(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 2) >> 1);
    }
}
