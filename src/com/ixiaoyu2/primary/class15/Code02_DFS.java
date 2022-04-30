package com.ixiaoyu2.primary.class15;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Code02_DFS {

    // 图的深度遍历

    public static void dfs(Point start) {
        if (start == null) {
            return;
        }
        Stack<Point> stack = new Stack<>();
        HashSet<Point> set = new HashSet<>();

        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.empty()) {
            Point cur = stack.pop();
            for (Point next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
