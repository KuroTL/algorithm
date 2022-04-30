package com.ixiaoyu2.primary.class15;

import java.util.*;

/**
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Code03_TopologicalOrderBFS {

    // 给定一个有向图，图节点的拓扑排序定义如下:
    //对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
    //拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
    //针对给定的有向图找到任意一种拓扑排序的顺序.

    private static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null || graph.size() == 0) {
            return ans;
        }

        HashMap<DirectedGraphNode, Integer> integerHashMap = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            integerHashMap.put(directedGraphNode, 0);
        }

        for (DirectedGraphNode directedGraphNode : graph) {
            for (DirectedGraphNode neighbor : directedGraphNode.neighbors) {
                integerHashMap.put(neighbor, integerHashMap.get(neighbor) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode directedGraphNode : integerHashMap.keySet()) {
            if (integerHashMap.get(directedGraphNode) == 0) {
                queue.add(directedGraphNode);
            }
        }
        while (!queue.isEmpty()) {
            DirectedGraphNode cur = queue.poll();
            ans.add(cur);
            for (DirectedGraphNode neighbor : cur.neighbors) {
                integerHashMap.put(neighbor, integerHashMap.get(neighbor) - 1);
                if (integerHashMap.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return ans;
    }

}
