package com.ixiaoyu2.primary.class15;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Code01_BFS {

    //图的宽度优先遍历
    public static void bfs(Point start) {
        if (start == null) {
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        HashSet<Point> set = new HashSet<>();

        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            System.out.println(cur.value);
            for (Point point : cur.nexts) {
                if (!set.contains(point)) {
                    queue.offer(point);
                    set.add(point);
                }
            }
        }
    }
}
