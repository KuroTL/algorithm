package com.ixiaoyu2.primary.class09;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author :Administrator
 * @date :2022/3/24 0024
 */
public class Code04_CopyListWithRandom {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int value) {
            this.val = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return val == node.val && Objects.equals(next, node.next) && Objects.equals(random, node.random);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next, random);
        }
    }

    public static Node copyListWithRandom(Node head) {
        // 0、1个节点 直接返回
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            next = cur.next;
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }

        cur = head;
        Node random = null;
        while (cur != null) {
            random = cur.random;
            cur.next.random = random != null ? random.next : null;
            cur = cur.next.next;
        }


        Node ans = head.next;
        Node ansCur = ans;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next = next;
            ansCur.next = next != null ? next.next : null;
            cur = next;
            ansCur = ansCur.next;
        }
        return ans;
    }

    public static Node copyRandomList1(Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node, Node> map = new HashMap<>(16);
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            // 新.next ->  cur.next克隆节点找到
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

}
