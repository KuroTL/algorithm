package com.ixiaoyu2.rookie.class04;

/**
 * @Author :Administrator
 * @Date :2022/2/22
 * @Description :com.msb.rookie.class04
 * @Version: 1.0
 */
public class SumOfTwoLinkedlist {
    public static void main(String[] args) {

    }

    public static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static ListNode sumOfTwoLinkedList(ListNode head1, ListNode head2) {

        int size1 = getLinkedListSize(head1);
        int size2 = getLinkedListSize(head2);
        ListNode l = size1 > size2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l;
        ListNode curS = s;
        ListNode end = curL;
        int carry = 0;
        int curSum = 0;

        while (curS != null) {
            curSum = carry + curL.data + curS.data;
            curL.data = curSum % 10;
            carry = curSum / 10;
            end = curL;
            curL = curL.next;
            curS = curS.next;
        }
        while (curL != null) {
            curSum = carry + curL.data;
            curL.data = curSum % 10;
            carry = curSum / 10;
            end = curL;
            curL = curL.next;
        }
        if (carry != 0) {
            end.next = new ListNode(carry, null);
        }

        return l;

    }

    public static int getLinkedListSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
