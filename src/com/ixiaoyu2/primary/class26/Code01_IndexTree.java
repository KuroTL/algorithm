package com.ixiaoyu2.primary.class26;

/**
 * IndexTree实现
 *
 * @author :Administrator
 * @date :2022/5/27 0027
 */
public class Code01_IndexTree {

    public static class IndexTree {

        private final int[] tree;
        private final int num;

        public IndexTree(int size) {
            num = size;
            tree = new int[num + 1];
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public void add(int index, int d) {
            while (index <= num) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    public static class Right {
        private final int[] nums;

        public Right(int size) {
            int n = size + 1;
            nums = new int[n + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }
    }

    public static void main(String[] args) {
        int n = 100;
        int v = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(n);
        Right test = new Right(n);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * n) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * v);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
