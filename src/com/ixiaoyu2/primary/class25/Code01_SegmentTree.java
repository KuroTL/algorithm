package com.ixiaoyu2.primary.class25;

/**
 * @author :Administrator
 * @date :2022/5/23 0023
 */
public class Code01_SegmentTree {

    //给定一个数组arr，用户希望你实现如下三个方法
    //1）void add(int L, int R, int V) :  让数组arr[L…R]上每个数都加上V
    //2）void update(int L, int R, int V) :  让数组arr[L…R]上每个数都变成V
    //3）int sum(int L, int R) :让返回arr[L…R]这个范围整体的累加和
    //怎么让这三个方法，时间复杂度都是O(logN)


    public static class SegmentTree {


        /**
         * 新数组长度
         */
        private final int len;
        /**
         * 处理给定数组，下标从1开始
         */
        private final int[] arr;
        /**
         * 线段树维护区间
         */
        private final int[] sum;
        /**
         * 累加和懒相加
         */
        private final int[] lazy;
        /**
         * 更新的值
         */
        private final int[] change;
        /**
         * 是否更新标记
         */
        private final boolean[] update;


        public SegmentTree(int[] origin) {
            len = origin.length + 1;
            arr = new int[len];
            System.arraycopy(origin, 0, arr, 1, len - 1);
            sum = new int[len << 2];
            lazy = new int[len << 2];
            change = new int[len << 2];
            update = new boolean[len << 2];
            build(1, len - 1, 1);
        }


        /**
         * arr数组 l~r范围，构建sum数组
         *
         * @param l     l下标
         * @param r     r下标
         * @param index l~r范围对应 sum数组的下标
         */
        private void build(int l, int r, int index) {
            if (l == r) {
                sum[index] = arr[l];
                return;
            }
            // l~r中点下标
            int mid = (l + r) >> 1;
            build(l, mid, index << 1);
            build(mid + 1, r, index << 1 | 1);
            pushUp(index);
        }

        /**
         * 求sum数组sumIndex位置的和  等于 左节点的值+右节点的值
         *
         * @param index 下标
         */
        private void pushUp(int index) {
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        /**
         * @param index    当前sum下标
         * @param leftNum  左数的节点个数
         * @param rightNum 右树的节点个数
         */
        private void pushDown(int index, int leftNum, int rightNum) {
            if (update[index]) {
                update[index << 1] = true;
                update[index << 1 | 1] = true;
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                lazy[index << 1] = 0;
                lazy[index << 1 | 1] = 0;
                sum[index << 1] = change[index] * leftNum;
                sum[index << 1 | 1] = change[index] * rightNum;
                update[index] = false;
            }
            if (lazy[index] != 0) {
                lazy[index << 1] += lazy[index];
                sum[index << 1] += lazy[index] * leftNum;
                lazy[index << 1 | 1] += lazy[index];
                sum[index << 1 | 1] += lazy[index] * rightNum;
                lazy[index] = 0;
            }
        }

        /**
         * 数组left-right区间的值更新为newValue
         *
         * @param left     数组区域左下标
         * @param right    数组区域右下标
         * @param newValue 更新为新值
         */
        public void update(int left, int right, int newValue) {
            update(left + 1, right + 1, newValue, 1, len - 1, 1);
        }

        private void update(int left, int right, int newValue, int l, int r, int index) {
            if (left <= l && r <= right) {
                update[index] = true;
                change[index] = newValue;
                sum[index] = newValue * (r - l + 1);
                lazy[index] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            // 向下分发更新任务
            pushDown(index, mid - l + 1, r - mid);

            if (left <= mid) {
                update(left, right, newValue, l, mid, index << 1);
            }
            if (right > mid) {
                update(left, right, newValue, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        /**
         * 数组left-right区间的每个数加value
         *
         * @param left  数组区域左下标
         * @param right 数组区域右下标
         * @param value 增加的值
         */
        public void add(int left, int right, int value) {
            add(left + 1, right + 1, value, 1, len - 1, 1);
        }

        private void add(int left, int right, int value, int l, int r, int index) {
            // left~right范围，l~r全部包含在范围内
            if (left <= l && r <= right) {
                sum[index] += value * (r - l + 1);
                lazy[index] += value;
                return;
            }
            // left~right范围，l~r没有全部包含在范围内
            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);

            if (left <= mid) {
                add(left, right, value, l, mid, index << 1);
            }
            if (right > mid) {
                add(left, right, value, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }


        /**
         * 返回数组left-right的累加和
         *
         * @param left  数组区域左下标
         * @param right 数组区域右下标
         * @return 区域的累加和
         */
        public long sum(int left, int right) {
            return sum(left + 1, right + 1, 1, len - 1, 1);
        }

        private long sum(int left, int right, int l, int r, int index) {
            if (left <= l && r <= right) {
                return sum[index];
            }

            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            long ans = 0;
            if (left <= mid) {
                ans += sum(left, right, l, mid, index << 1);
            }
            if (right > mid) {
                ans += sum(left, right, mid + 1, r, index << 1 | 1);
            }
            return ans;
        }

    }

    // 采取遍历做法

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length];
            System.arraycopy(origin, 0, arr, 0, origin.length);
        }

        public void update(int left, int right, int newValue) {
            for (int i = left; i <= right; i++) {
                arr[i] = newValue;
            }
        }


        public void add(int left, int right, int value) {
            for (int i = left; i <= right; i++) {
                arr[i] += value;
            }
        }

        public long sum(int left, int right) {
            long ans = 0;
            for (int i = left; i <= right; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    // 测试

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);

            SegmentTree seg = new SegmentTree(origin);
            Right rig = new Right(origin);
            // 原数组长度
            int n = origin.length;

            // 测试添加和更新
            for (int j = 0; j < addOrUpdateTimes; j++) {
                // 0~length-1随机整数
                int num1 = (int) (Math.random() * n);
                int num2 = (int) (Math.random() * n);

                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);
                int value = (int) (Math.random() * max) - (int) (Math.random() * max);

                if (Math.random() < 0.5) {
                    seg.add(left, right, value);
                    rig.add(left, right, value);
                } else {
                    seg.update(left, right, value);
                    rig.update(left, right, value);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * n);
                int num2 = (int) (Math.random() * n);
                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);

                long ans1 = seg.sum(left, right);
                long ans2 = rig.sum(left, right);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        Right rightQ = new Right(origin);
        // 操作区间的开始位置 -> 可变
        int left = 2;
        // 操作区间的结束位置 -> 可变
        int right = 5;
        // 要加的数字或者要更新的数字 -> 可变
        int value = 4;
        // 区间生成，必须在[S,N]整个范围上build
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(left, right, value);
        rightQ.add(left, right, value);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(left, right, value);
        rightQ.update(left, right, value);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.sum(left, right);
        long sum1 = rightQ.sum(left, right);
        System.out.println(sum);
        System.out.println(sum1);
        System.out.println(sum == sum1);


        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

}
