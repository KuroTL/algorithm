package com.ixiaoyu2.primary.class08;

import java.util.Objects;

/**
 * @author :Administrator
 * @date :2022/3/20 0020
 */
public class Code01_TrieTree {

    // 数组实现前缀树
    static class Node {
        public int pass;
        public int end;
        public Node[] next;
        private static final int DEFAULT_NEXT_NUM = 26;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[DEFAULT_NEXT_NUM];
        }
    }

    static class TrieTree {
        private final Node root;

        public TrieTree() {
            this.root = new Node();
        }

        public void insert(String word) {
            assert Objects.nonNull(word);
            char[] chars = word.toCharArray();
            Node node = root;
            root.pass++;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        // 删掉某个字符串，可以重复删除，每次算1个
        public void delete(String word) {
            if (search(word) > 0) {
                root.pass--;
                Node node = root;
                char[] chars = word.toCharArray();
                int index = 0;
                for (char aChar : chars) {
                    index = aChar - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        // 某个字符串在结构中还有几个
        public int search(String word) {
            assert Objects.nonNull(word);
            Node node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.pass == 0 || node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        // 查询有多少个字符串，是以str做前缀的
        public int prefixNumber(String pre) {
            assert Objects.nonNull(pre);
            Node node = root;
            char[] chars = pre.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.pass;
        }

    }

}
