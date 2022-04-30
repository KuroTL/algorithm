package com.ixiaoyu2.primary.class15;

import java.util.*;

/**
 * @author :Administrator
 * @date :2022/4/17 0017
 */
public class Code07_Prim {

    //1）可以从任意节点出发来寻找最小生成树
    //2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
    //3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
    //4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
    //5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
    //6）当所有点都被选取，最小生成树就得到了

    public Set<Edge> generateTreeByPrim(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        if (graph == null) {
            return ans;
        }
        HashSet<Point> selected = new HashSet<>();
        HashMap<Integer, Point> points = graph.points;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        for (Point point : points.values()) {
            if (!selected.contains(point)) {
                selected.add(point);
                while (!priorityQueue.isEmpty()) {
                    priorityQueue.addAll(point.edges);
                    Edge edge = priorityQueue.poll();
                    Point to = edge.to;
                    if (!selected.contains(to)) {
                        ans.add(edge);
                        selected.add(edge.to);
                        priorityQueue.addAll(to.edges);
                    }
                }
            }
        }
        return ans;
    }
}
