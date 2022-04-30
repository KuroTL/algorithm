package com.ixiaoyu2.primary.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Code04_TopologicalOrderDFS1 {


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

    // 点x的深度>点y的深度，那么拓扑序 x<=y

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null || graph.size() == 0) {
            return ans;
        }
        HashMap<DirectedGraphNode, Info> map = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            process(directedGraphNode, map);
        }

        ArrayList<Info> arrayList = new ArrayList<>();
        map.forEach((k, v) -> arrayList.add(v));
        arrayList.sort((a, b) -> b.deep - a.deep);

        for (Info info : arrayList) {
            ans.add(info.node);
        }

        return ans;
    }


    private static class Info {
        DirectedGraphNode node;
        int deep;

        public Info(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    private Info process(DirectedGraphNode cur, HashMap<DirectedGraphNode, Info> map) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        int deep = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
            deep = Math.max(deep, process(neighbor, map).deep);
        }

        Info info = new Info(cur, deep + 1);
        map.put(cur, info);
        return info;
    }

}
