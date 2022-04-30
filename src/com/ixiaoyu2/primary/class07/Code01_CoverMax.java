package com.ixiaoyu2.primary.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/3/16 0016
 * @description :com.msb.primary.class07
 * @version: 1.0
 */
public class Code01_CoverMax {
    //给定很多线段，每个线段都有两个数[start, end]，
    //表示线段开始位置和结束位置，左右都是闭区间
    //规定：
    //1）线段的开始和结束位置一定都是整数值
    //2）线段重合区域的长度必须>=1
    //返回线段最多重合区域中，包含了几条线段


    public static int coverMax(int[][] lines) {
        if (lines == null || lines.length < 1) {
            return 0;
        }
        Arrays.sort(lines, Comparator.comparingInt(e -> e[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>(lines.length);
        int ans = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i][0]) {
                heap.poll();
            }
            heap.add(lines[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }


    /*以下为比较器，用于测试*/

    public static int comparator(int[][] lines) {
        if (lines == null || lines.length < 1) {
            return 0;
        }
        int minStart = lines[0][0];
        int maxEnd = lines[0][1];
        for (int i = 1; i < lines.length; i++) {
            minStart = Math.min(minStart, lines[i][0]);
            maxEnd = Math.max(maxEnd, lines[i][1]);
        }
        int[] count = new int[maxEnd - minStart];
        double newIndex = minStart + 0.5;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                if (newIndex > lines[j][0] && newIndex < lines[j][1]) {
                    count[i]++;
                }
            }
            newIndex++;
        }
        int ans = count[0];
        for (int i = 1; i < count.length; i++) {
            ans = Math.max(ans, count[i]);
        }
        return ans;
    }

    public static int[][] generateLines(int maxNum, int minStart, int maxEnd) {
        int[][] lines = new int[(int) (Math.random() * maxNum) + 1][2];
        for (int i = 0; i < lines.length; i++) {
            lines[i][0] = minStart + (int) (Math.random() * (maxEnd - minStart + 1));
            lines[i][1] = lines[i][0] + (int) (Math.random() * (maxEnd - minStart + 1) + 1);
        }
        return lines;
    }

    public static void main(String[] args) {

        int[][] lines1 = {{4, 9}, {1, 4}, {7, 15}, {2, 4}, {4, 6}, {3, 7}};


        // 底层堆结构，heap
        PriorityQueue<int[]> heap = new PriorityQueue<>((e1, e2) -> {
            return e1[0] - e2[0];
        });
        heap.add(lines1[0]);
        heap.add(lines1[1]);
        heap.add(lines1[2]);
        heap.add(lines1[3]);
        heap.add(lines1[4]);
        heap.add(lines1[5]);

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            System.out.println(cur[0] + "," + cur[1]);
        }

        System.out.println("test begin");
        int maxNum = 100;
        int minStart = 0;
        int maxEnd = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(maxNum, minStart, maxEnd);
            int ans1 = coverMax(lines);
            int ans2 = comparator(lines);
            if (ans1 != ans2) {
                System.out.println("算法出错啦!");
                System.out.println(ans1);
                System.out.println(ans2);
                for (int i1 = 0; i1 < lines.length; i1++) {
                    System.out.print(Arrays.toString(lines[i]) + " ");
                }
                break;
            }
        }
        System.out.println("test end");
    }

}
