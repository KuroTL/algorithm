package com.ixiaoyu2.primary.class27;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 *
 * @author :Long
 * @date :2022/5/27 0027
 */
public class Code01_AC2 {

    public static class ACAutomation {

        private static class Node {

            String end;
            boolean endUsed;
            Node[] next;
            Node fail;

            public Node() {
                end = null;
                endUsed = false;
                next = new Node[26];
                fail = null;
            }


        }


        private Node root;
        private int num;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            assert s != null;
            char[] chars = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = s;
            num++;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cFail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < cur.next.length; i++) {
                    if (cur.next[i] != null) {
                        cur.next[i].fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.next[i] != null) {
                                cur.next[i].fail = cFail.next[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(cur.next[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            assert content != null;
            char[] chars = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> res = new ArrayList<>(num);
            for (char aChar : chars) {
                index = aChar - 'a';
                while (cur.next[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.next[index] != null ? cur.next[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUsed) {
                        break;
                    }
                    if (follow.end != null) {
                        res.add(follow.end);
                        follow.endUsed = true;
                    }
                    follow = follow.fail;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdkhe");
        for (String word : contains) {
            System.out.println(word);
        }
    }


}
