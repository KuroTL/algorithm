package com.ixiaoyu2.primary.class15;

/**
 * @author :Administrator
 * @date :2022/4/17 0017
 */
public class Code08_LeastPathByPrim {

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和

    public static int leastPathByPrim(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return 0;
        }

        int size = graph.length;
        int[] distance = new int[size];
        boolean[] visited = new boolean[size];
        visited[0] = true;
        for (int i = 0; i < size; i++) {
            distance[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 0; i < distance.length; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minIndex = j;
                }
            }

            if (minIndex == -1) {
                return sum;
            }

            visited[minIndex] = true;
            sum += minDistance;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distance[j] > graph[minIndex][j]) {
                    distance[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 3, 1}, {3, 0, 4}, {1, 4, 0}};
        int i = leastPathByPrim(graph);
        System.out.println(i);
    }
}
