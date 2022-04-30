package com.ixiaoyu2.primary.class08;

import java.util.HashMap;

/**
 * @author :Administrator
 * @date :2022/3/20 0020
 */
public class Code03_TrieTreeComparator {


    static class TrieTreeComparator {
        private HashMap<String, Integer> hashMap;

        public TrieTreeComparator() {
            hashMap = new HashMap<>();
        }

        public void insert(String word) {
            if (!hashMap.containsKey(word)) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, hashMap.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (hashMap.containsKey(word)) {
                if (hashMap.get(word) == 1) {
                    hashMap.remove(word);
                } else {
                    hashMap.put(word, hashMap.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return hashMap.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String word : hashMap.keySet()) {
                if (word.startsWith(pre)) {
                    count += hashMap.get(word);
                }
            }
            return count;
        }
    }
}
