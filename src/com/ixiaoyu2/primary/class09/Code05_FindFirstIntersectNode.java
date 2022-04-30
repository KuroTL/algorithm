package com.ixiaoyu2.primary.class09;

import com.sun.istack.internal.NotNull;

/**
 * @author :Administrator
 * @date :2022/3/27 0027
 */
public class Code05_FindFirstIntersectNode {
    //给定两个可能有环也可能无环的单链表，头节点head1和head2。
    // 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
    //【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。


    public static Node findFirstIntersectNode(Node head1, Node head2) {
        // 两个链表都为空
        if (head1 == null || head2 == null) {
            return null;
        }
        // 两个链表都不为空

        Node loop1 = isLoop(head1);
        Node loop2 = isLoop(head2);
        if (loop1 == null && loop2 == null) {
            return noLoopIntersect(head1, head2);
        } else if (loop1 == null ^ loop2 == null) {
            return oneLoopIntersect(head1, head2);
        } else {
            return twoLoopIntersect(head1, head2);
        }
    }

    // 判断链表是否有环，有环则返还进入环的节点，无环则返回null

    private static Node isLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;

        // 从循环里出来时,slow==fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    /*
     * 两个可能有环的链表相交，有以下几种情况：
     * 1 两个无环链表不相交
     * 2 两个无环链表相交
     * 3 一个有环、一个无环 不可能相交
     * 4 两个有环链表不相交
     * 5 两个有环链表相交
     *   i 相交节点在第一个入环节点之前
     *   ii 相交节点环上
     * */

    //  返回两个无环链表相交节点，不相交返回null（情况1、2)

    private static Node noLoopIntersect(@NotNull Node head1, @NotNull Node head2) {
        Node end1 = head1;
        Node end2 = head2;
        //至少有一个节点
        int size1 = 1;
        int size2 = 1;
        while (end1.next != null) {
            size1++;
            end1 = end1.next;
        }
        while (end2.next != null) {
            size2++;
            end2 = end2.next;
        }
        // 两个无环链表相交，那么最后一个节点一定相同，不同则表示两个链表不相交
        if (end1 != end2) {
            return null;
        }
        // end1节点指向长的那个链表，end2指向短的那个链表
        end1 = size1 > size2 ? head1 : head2;
        end2 = end1 == head1 ? head2 : head1;

        for (int i = 0; i < Math.abs(size1 - size2); i++) {
            end1 = end1.next;
        }

        while (end1 != end2) {
            end1 = end1.next;
            end2 = end2.next;
        }
        return end1;
    }

    //  一个有环，一个无环,不可能相交(情况3)

    private static Node oneLoopIntersect(Node head1, Node head2) {
        return null;
    }

    // 返回两个有环链表相交第一个节点，不相交返回null(情况4、5)

    private static Node twoLoopIntersect(Node head1, Node head2) {
        Node loopNode1 = isLoop(head1);
        Node loopNode2 = isLoop(head2);
        // 相交节点在第一个入环节点之前
        if (loopNode1 == loopNode2) {
            // 将第一个入环节点当成链表最后一个节点，类似情况2
            Node end1 = head1;
            Node end2 = head2;
            int n = 0;
            while (end1 != loopNode1) {
                n++;
                end1 = end1.next;
            }
            while (end2 != loopNode2) {
                n--;
                end2 = end2.next;
            }
            // end1 指向长的链表
            end1 = n > 0 ? head1 : head2;
            end2 = end1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                end1 = end1.next;
                n--;
            }
            while (end1 != end2) {
                end1 = end1.next;
                end2 = end2.next;
            }
            return end1;
        } else {
            Node loopNodeCur = loopNode1.next;
            while (loopNodeCur != loopNode1) {
                // 相交节点在环上
                if (loopNodeCur == loopNode2) {
                    return loopNode1;
                }
                loopNodeCur = loopNodeCur.next;
            }
            // 不相交
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);

    }
}
