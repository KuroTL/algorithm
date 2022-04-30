package com.ixiaoyu2.primary.class15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author :Administrator
 * @date :2022/4/17 0017
 */
public class Code09_LeastDistanceByDijkstra {

    //1）Dijkstra算法必须指定一个源点
    //2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
    //3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
    //4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了

    public static HashMap<Point, Integer> dijkstra1(Point from) {
        HashMap<Point, Integer> distanceMap = new HashMap<>();
        if (from == null) {
            return distanceMap;
        }
        HashSet<Point> set = new HashSet<>();
        distanceMap.put(from, 0);
        Point point = getMinDistanceUnselectedPoint(distanceMap, set);
        while (point != null) {
            int distance = distanceMap.get(point);
            for (Edge edge : point.edges) {
                Point to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            set.add(point);
            point = getMinDistanceUnselectedPoint(distanceMap, set);
        }
        return distanceMap;
    }

    private static Point getMinDistanceUnselectedPoint(HashMap<Point, Integer> distanceMap, HashSet<Point> selected) {
        int minDistance = Integer.MAX_VALUE;
        Point point = null;
        for (Map.Entry<Point, Integer> entry : distanceMap.entrySet()) {
            Point cur = entry.getKey();
            int distance = entry.getValue();
            if (!selected.contains(cur) && distance < minDistance) {
                point = cur;
                minDistance = distance;
            }
        }
        return point;
    }


    public static HashMap<Point, Integer> dijkstra2(Point from, int size) {
        HashMap<Point, Integer> ans = new HashMap<>();
        if (from == null) {
            return ans;
        }

        PointHeap pointHeap = new PointHeap(size);
        pointHeap.addOrUpdateOrIgnore(from, 0);
        while (!pointHeap.isEmpty()) {
            PointInfo pointInfo = pointHeap.pop();
            Point cur = pointInfo.point;
            int dis = pointInfo.distance;
            cur.edges.forEach(edge -> pointHeap.addOrUpdateOrIgnore(edge.to, edge.weight + dis));
            ans.put(cur, dis);
        }
        return ans;
    }

    private static class PointInfo {
        Point point;
        int distance;

        public PointInfo(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    private static class PointHeap {

        private Point[] heap;
        private HashMap<Point, Integer> indexMap;
        private HashMap<Point, Integer> distanceMap;
        int size;

        public PointHeap(int size) {
            heap = new Point[size];
            indexMap = new HashMap<>(size);
            distanceMap = new HashMap<>(size);
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Point point, int distance) {
            if (!isEntered(point)) {
                heap[size] = point;
                indexMap.put(point, size);
                distanceMap.put(point, distance);
                heapInsert(point, size++);
            } else if (inHeap(point)) {
                distanceMap.put(point, Math.min(distanceMap.get(point), distance));
                heapInsert(point, indexMap.get(point));
            }
        }

        public PointInfo pop() {
            PointInfo info = new PointInfo(heap[0], distanceMap.get(heap[0]));
            swap(0, size - 1);
            indexMap.put(heap[size - 1], -1);
            distanceMap.remove(heap[size - 1]);
            heap[size - 1] = null;
            heapfy(0, --size);
            return info;
        }


        private boolean inHeap(Point point) {
            return isEntered(point) && indexMap.get(point) != -1;
        }

        private boolean isEntered(Point point) {
            return indexMap.containsKey(point);
        }

        private void heapInsert(Point point, int index) {
            while (distanceMap.get(heap[(index - 1) / 2]) > distanceMap.get(heap[index])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapfy(int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {
                int small = left + 1 < size && distanceMap.get(heap[left + 1]) < distanceMap.get(heap[left]) ? left + 1 : left;
                small = distanceMap.get(heap[small]) < distanceMap.get(heap[index]) ? small : index;
                if (small == index) {
                    break;
                }
                swap(small, index);
                index = small;
                left = 2 * index + 1;
            }
        }

        private void swap(int i, int j) {
            Point pointI = heap[i];
            Point pointJ = heap[j];
            heap[i] = pointJ;
            heap[j] = pointI;
            indexMap.put(pointI, j);
            indexMap.put(pointJ, i);
        }
    }

}
