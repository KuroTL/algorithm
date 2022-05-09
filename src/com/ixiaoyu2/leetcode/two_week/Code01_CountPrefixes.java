package com.ixiaoyu2.leetcode.two_week;

import java.util.Objects;

/**
 * 统计是给定字符串前缀的字符串数目
 * https://leetcode.cn/problems/count-prefixes-of-a-given-string/
 *
 * @author :Administrator
 * @date :2022/4/30 0030
 */
public class Code01_CountPrefixes {

    //给你一个字符串数组 words 和一个字符串 s ，其中 words[i] 和 s 只包含 小写英文字母 。
    //请你返回 words 中是字符串 s 前缀 的 字符串数目 。
    //一个字符串的 前缀 是出现在字符串开头的子字符串。子字符串 是一个字符串中的连续一段字符序列。

    public static int countPrefixes(String[] words, String s) {

        TrieTree tree = new TrieTree();
        tree.insert(s);
        for (String word : words) {
            tree.insert(word);
        }

        char[] chars = s.toCharArray();
        int ans = 0;
        Node root = tree.root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            ans += root.next[index].end;
            root = root.next[index];
        }
        return ans - 1;
    }

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

    private static class TrieTree {
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

    public static void main(String[] args) {
        String[] words = {"a", "a"};
        String s = "aa";
        int i = countPrefixes(words, s);
        System.out.println(i);
    }
}
