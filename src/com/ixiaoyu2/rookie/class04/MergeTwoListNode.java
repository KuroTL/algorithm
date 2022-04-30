package com.ixiaoyu2.rookie.class04;

/**
 * @Author :Administrator
 * @Date :2022/2/22
 * @Description :com.msb.rookie.class04
 * @Version: 1.0
 */
public class MergeTwoListNode {

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int data, ListNode next) {
            this.val = data;
            this.next = next;
        }
    }

    public ListNode mergeTwoListNode(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode cur = head;
        ListNode cur1 = head.next;
        ListNode cur2 = head == list1 ? list2 : list1;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}
