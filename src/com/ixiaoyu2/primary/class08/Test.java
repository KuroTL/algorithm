package com.ixiaoyu2.primary.class08;

/**
 * @author :Administrator
 * @date :2022/3/20 0020
 */
public class Test {

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 26);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        Code01_TrieTree.TrieTree trieTree1 = new Code01_TrieTree.TrieTree();
        Code02_TrieTree.TrieTree trieTree2 = new Code02_TrieTree.TrieTree();
        Code03_TrieTreeComparator.TrieTreeComparator comparator = new Code03_TrieTreeComparator.TrieTreeComparator();
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            for (int i1 = 0; i1 < arr.length; i1++) {
                double random = Math.random();
                if (random < 0.25) {
                    trieTree1.insert(arr[i1]);
                    trieTree2.insert(arr[i1]);
                    comparator.insert(arr[i1]);
                } else if (random < 0.5) {
                    trieTree1.delete(arr[i1]);
                    trieTree2.delete(arr[i1]);
                    comparator.delete(arr[i1]);
                } else if (random < 0.75) {
                    int ans1 = trieTree1.search(arr[i1]);
                    int ans2 = trieTree2.search(arr[i1]);
                    int ans3 = comparator.search(arr[i1]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("出错啦");
                        break;
                    }
                } else {
                    int ans1 = trieTree1.prefixNumber(arr[i1]);
                    int ans2 = trieTree2.prefixNumber(arr[i1]);
                    int ans3 = comparator.prefixNumber(arr[i1]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("出错啦");
                        break;
                    }
                }
            }
        }
        System.out.println("测试结束");
    }

}
