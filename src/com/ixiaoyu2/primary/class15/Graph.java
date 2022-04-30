package com.ixiaoyu2.primary.class15;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图对象类
 *
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Graph {

    /**
     * 组成图的点集
     */
    protected HashMap<Integer, Point> points;
    /**
     * 组成图的边集
     */
    protected HashSet<Edge> edges;

    public Graph() {
        points = new HashMap<>();
        edges = new HashSet<>();
    }
}
