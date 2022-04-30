package com.ixiaoyu2.primary.class08;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author :Administrator
 * @date :2022/3/20 0020
 */
public class Code02_TrieTree {
    //HashMap实现前缀树
    static class Node {
        private int pass;
        private int end;
        private HashMap<Integer, Node> next;

        public Node() {
            pass = 0;
            end = 0;
            next = new HashMap<>(26);
        }
    }

    static class TrieTree {
        private Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word) {
            assert Objects.nonNull(word);
            root.pass++;
            Node node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (!node.next.containsKey(index)) {
                    node.next.put(index, new Node());
                }
                node = node.next.get(index);
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
                    if (--node.next.get(index).pass == 0) {
                        node.next.remove(index);
                        return;
                    }
                    node = node.next.get(index);
                }
                node.end--;
            }
        }

        // 某个字符串在结构中还有几个
        public int search(String word) {
            assert word != null;
            Node node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.pass == 0 || !node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.end;
        }

        // 查询有多少个字符串，是以str做前缀的
        public int prefixNumber(String pre) {
            assert pre != null;
            Node node = root;
            char[] chars = pre.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next.get(index) == null) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.pass;
        }
    }
}
