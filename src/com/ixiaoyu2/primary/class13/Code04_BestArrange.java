package com.ixiaoyu2.primary.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/4/9 0009
 */
public class Code04_BestArrange {

    /*一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
    给你每一个项目开始的时间和结束的时间
    你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
    返回最多的宣讲场次。
    */

    /**
     * 会议类 开始时间和结束时间
     */
    private static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        // 按结束时间组织小根堆
        PriorityQueue<Program> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(program -> program.end));
        priorityQueue.addAll(Arrays.asList(programs));
        //
        int timeLine = 0;
        int ans = 0;
        while (!priorityQueue.isEmpty()) {
            Program cur = priorityQueue.poll();
            if (cur.start >= timeLine) {
                ans++;
                timeLine = cur.end;
            }
        }
        return ans;
    }

    public static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        return precess(programs, 0, 0);
    }


    /**
     * @param programs 剩余会议
     * @param done     开的会议次数
     * @param timeLine 时间点
     * @return 会议次数
     */
    private static int precess(Program[] programs, int done, int timeLine) {
        if (programs == null || programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] remainingProgram = remainingProgram(programs, i);
                max = Math.max(max, precess(remainingProgram, done + 1, programs[i].end));
            }
        }
        return max;
    }

    private static Program[] remainingProgram(Program[] programs, int index) {
        Program[] ans = new Program[programs.length - 1];
        int i = 0;
        for (int j = 0; j < programs.length; j++) {
            if (j != index) {
                ans[i++] = programs[j];
            }
        }
        return ans;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            int ans1 = bestArrange(programs);
            int ans2 = bestArrange2(programs);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(Arrays.toString(programs));
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}