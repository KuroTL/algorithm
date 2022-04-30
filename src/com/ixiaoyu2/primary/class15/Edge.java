package com.ixiaoyu2.primary.class15;

/**
 * 边对象类
 *
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Edge {

    /**
     * 边的起点
     */
    protected Point from;

    /**
     * 边的终点
     */
    protected Point to;
    /**
     * 边的权重
     */
    protected int weight;

    public Edge(Point from, Point to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
