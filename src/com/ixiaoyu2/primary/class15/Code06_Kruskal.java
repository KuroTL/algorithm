package com.ixiaoyu2.primary.class15;

import java.util.*;

/**
 * @author :Administrator
 * @date :2022/4/17 0017
 */
public class Code06_Kruskal {

    //1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
    //2）当前的边要么进入最小生成树的集合，要么丢弃
    //3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
    //4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
    //5）考察完所有边之后，最小生成树的集合也得到了

    public Set<Edge> generateTreeByKruskal(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        if (graph == null) {
            return ans;
        }
        HashSet<Edge> edges = graph.edges;
        UnionSet unionSet = new UnionSet(graph.points.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        edges.forEach(priorityQueue::offer);

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionSet.isSameSet(edge.from, edge.to)) {
                ans.add(edge);
                unionSet.union(edge.from, edge.to);
            }
        }
        return ans;
    }

    private static class UnionSet {
        HashMap<Point, Point> parenets;
        HashMap<Point, Integer> size;
        List<Point> helpList;

        public UnionSet(Collection<Point> points) {
            parenets = new HashMap<>();
            size = new HashMap<>();
            helpList = new ArrayList<>();
            makeSet(points);
        }

        private void makeSet(Collection<Point> points) {
            for (Point point : points) {
                parenets.put(point, point);
                size.put(point, 1);
            }
        }

        private Point find(Point point) {
            while (point != parenets.get(point)) {
                helpList.add(point);
                point = parenets.get(point);
            }
            for (Point subPoint : helpList) {
                parenets.put(subPoint, point);
            }
            helpList.clear();
            return point;
        }

        public boolean isSameSet(Point point1, Point point2) {
            Point f1 = find(point1);
            Point f2 = find(point2);
            return f1 == f2;
        }

        public void union(Point point1, Point point2) {
            if (point1 == null || point2 == null) {
                return;
            }
            Point f1 = find(point1);
            Point f2 = find(point2);
            if (f1 != f2) {
                if (size.get(f1) > size.get(f2)) {
                    parenets.put(f2, f1);
                    size.put(f1, size.get(f1) + size.get(f2));
                    size.remove(f2);
                } else {
                    parenets.put(f1, f2);
                    size.put(f2, size.get(f1) + size.get(f2));
                    size.remove(f1);
                }
            }
        }
    }
}
