package com.ixiaoyu2.primary.class11;

import java.util.List;

/**
 * @author :Administrator
 * @date :2022/3/31 0031
 */
public class Node {

    protected int val;
    protected List<Node> children;

    protected Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
