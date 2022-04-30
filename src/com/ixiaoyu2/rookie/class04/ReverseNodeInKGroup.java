package com.ixiaoyu2.rookie.class04;

/**
 * @Author :Administrator
 * @Date :2022/2/22
 * @Description :com.msb.rookie.class04
 * @Version: 1.0
 */
public class ReverseNodeInKGroup {

    class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    //给定一个单链表的头节点head，和一个正数k
    //实现k个节点的小组内部逆序，如果最后一组不够k个就不调整
    //例子:
    //调整前：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8，k = 3
    //调整后：3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8

    public static <E> Node<E> reverseNodeInKGroup(Node<E> head, int k) {

        Node<E> start = head;
        Node<E> end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        head = end;
        reverseLinkedList(start, end);
        Node<E> lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverseLinkedList(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;


    }

    private static <E> int getLinkedListLength(Node<E> head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private static <E> void reverseLinkedList(Node<E> start, Node<E> end) {
        end = end.next;
        Node<E> pre = null;
        Node<E> cur = start;
        Node<E> next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    private static <E> Node<E> getKGroupEnd(Node<E> start, int k) {
        while (start != null && --k != 0) {
            start = start.next;
        }
        return start;
    }
}
