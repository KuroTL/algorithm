package com.ixiaoyu2.primary.class09;

import java.util.ArrayList;

/**
 * @author :Administrator
 * @date :2022/3/24 0024
 */
public class Code03_SmallEqualBig {

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node smallEqualBig(Node head, int k) {
        // 只有0、1个节点情况下直接返回
        if (head == null || head.next == null) {
            return head;
        }
        //至少2个节点的情况

        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;


        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < k) {
                if (smallHead == null) {
                    smallHead = head;
                } else {
                    smallTail.next = head;
                }
                smallTail = head;
            } else if (head.value == k) {
                if (equalHead == null) {
                    equalHead = head;
                } else {
                    equalTail.next = head;
                }
                equalTail = head;
            } else {
                if (bigHead == null) {
                    bigHead = head;
                } else {
                    bigTail.next = head;
                }
                bigTail = head;
            }
            head = next;
        }

        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }

        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }

    public static Node smallEqualBig2(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        partition(list, k);


        for (int i = 0; i < list.size(); i++) {
            head = list.get(i);
            if (i == list.size() - 1) {
                head.next = null;
            } else {
                head.next = list.get(i + 1);
            }
        }
        return list.get(0);
    }

    private static void partition(ArrayList<Node> list, int k) {
        int smallR = -1;
        int bigL = list.size() - 1;

        for (int i = 0; i <= bigL; ) {
            if (list.get(i).value < k) {
                swap(list, ++smallR, i++);
            } else if (list.get(i).value == k) {
                i++;
            } else {
                swap(list, bigL--, i);
            }
        }
    }

    private static void swap(ArrayList<Node> list, int i, int j) {
        Node tmp = list.set(i, list.get(j));
        list.set(j, tmp);
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = smallEqualBig(head1, 5);
        head1 = smallEqualBig2(head1, 5);
        printLinkedList(head1);

    }
}
