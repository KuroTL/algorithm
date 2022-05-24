package com.ixiaoyu2.primary.class25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * <a href="https://leetcode.com/problems/falling-squares/">leetcode链接</a>
 *
 * @author :Administrator
 * @date :2022/5/23 0023
 */
public class Code02_FallSquares {

    /*
    想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
    下面是这个游戏的简化版：
    1）只会下落正方形积木
    2）[a,b]-> 代表一个边长为b的正方形积木，积木左边缘沿着X=a这条线从上方掉落
    3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
    4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
    给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，返回每一次掉落之后的最大高度
    */

    public List<Integer> fallingSquares(int[][] positions) {

        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return res;
        }
        HashMap<Integer, Integer> map = index(positions);
        int n = map.size();
        SegmentTree segmentTree = new SegmentTree(n);
        int max = 0;
        for (int[] position : positions) {
            int left = map.get(position[0]);
            int right = map.get(position[0] + position[1] - 1);
            int height = segmentTree.queryMax(left, right, 1, 1, n) + position[1];
            res.add(max = Math.max(height, max));
            segmentTree.update(left, right, height, 1, 1, n);
        }
        return res;
    }


    /**
     * 将坐标转换为数组下标
     */
    private HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int[] position : positions) {
            treeSet.add(position[0]);
            treeSet.add(position[0] + position[1] - 1);
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (Integer index : treeSet) {
            hashMap.put(index, ++count);
        }
        return hashMap;
    }

    private static class SegmentTree {
        private final int[] max;
        private final int[] change;
        private final boolean[] update;

        public SegmentTree(int n) {
            int len = n + 1;
            max = new int[len << 2];
            change = new int[len << 2];
            update = new boolean[len << 2];
        }

        private void pushUp(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        private void pushDown(int index, int leftNum, int rightNum) {
            if (update[index]) {
                update[index << 1] = update[index];
                update[index << 1 | 1] = update[index];
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                max[index << 1] = change[index];
                max[index << 1 | 1] = change[index];
                update[index] = false;
            }
        }

        public void update(int left, int right, int newValue, int index, int l, int r) {
            if (l >= left && r <= right) {
                update[index] = true;
                change[index] = newValue;
                max[index] = newValue;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            if (left <= mid) {
                update(left, right, newValue, index << 1, l, mid);
            }
            if (right > mid) {
                update(left, right, newValue, index << 1 | 1, mid + 1, r);
            }
            pushUp(index);
        }

        public int queryMax(int left, int right, int index, int l, int r) {
            if (l >= left && r <= right) {
                return max[index];
            }
            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);

            int leftMax = 0;
            int rightMax = 0;

            if (left <= mid) {
                leftMax = queryMax(left, right, index << 1, l, mid);
            }
            if (right > mid) {
                rightMax = queryMax(left, right, index << 1 | 1, mid + 1, r);
            }
            return Math.max(leftMax, rightMax);
        }
    }


}
