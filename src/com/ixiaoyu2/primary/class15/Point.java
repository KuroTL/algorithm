package com.ixiaoyu2.primary.class15;

import java.util.ArrayList;
import java.util.List;

/**
 * 点对象类
 *
 * @author :Administrator
 * @date :2022/4/15 0015
 */
public class Point {

    /**
     * 该点的值
     */

    protected int value;
    /**
     * 入度 指向该点的边的数量
     */
    protected int in;
    /**
     * 出度，该点指向其他点，边的数量
     */
    protected int out;
    /**
     * 该点指向的点
     */
    protected List<Point> nexts;
    /**
     * 从该点出发的边
     */
    protected List<Edge> edges;


    public Point(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
