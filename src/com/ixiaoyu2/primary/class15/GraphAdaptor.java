package com.ixiaoyu2.primary.class15;

/**
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class GraphAdaptor {


    /**
     * 邻接矩阵表示法转换为对象标识符
     *
     * @param matrix 为n*3的矩阵，矩阵每一行为点到点的表示，第一个值为权重，第二个值为起点，第三个值为终点
     * @return 图对象
     */
    public Graph matrixToGraph(int[][] matrix) {
        Graph graph = new Graph();
        if (matrix == null || matrix.length == 0) {
            return graph;
        }

        for (int[] arr : matrix) {
            int weight = arr[0];
            int from = arr[1];
            int to = arr[2];
            if (!graph.points.containsKey(arr[1])) {
                graph.points.put(arr[1], new Point(arr[1]));
            }
            if (!graph.points.containsKey(arr[2])) {
                graph.points.put(arr[2], new Point(arr[2]));
            }

            Point fromPoint = graph.points.get(from);
            Point toPoint = graph.points.get(to);
            Edge edge = new Edge(fromPoint, toPoint, weight);
            fromPoint.nexts.add(toPoint);
            fromPoint.out++;
            fromPoint.edges.add(edge);
            toPoint.in++;
            graph.edges.add(edge);
        }
        return graph;
    }
}
