package com.ixiaoyu2.rookie.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author :Administrator
 * @Date :2022/2/24
 * @Description :com.msb.rookie.class06
 * @Version: 1.0
 */
public class MergeKLists {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                listNodes.add(lists[i]);
            }
        }
        if (listNodes.isEmpty()) {
            return null;
        }
        ListNode head = listNodes.poll();
        ListNode pre = head;
        if (pre.next != null) {
            listNodes.add(pre.next);
        }
        while (!listNodes.isEmpty()) {
            ListNode cur = listNodes.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                listNodes.add(cur.next);
            }
        }
        return head;
    }

    class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}
