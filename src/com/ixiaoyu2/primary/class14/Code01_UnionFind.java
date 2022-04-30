package com.ixiaoyu2.primary.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/4/10 0010
 */
public class Code01_UnionFind {

    private static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }


    public static class UnionFind<V> {
        /**
         * 将传入的节点类型包装为Node类型
         */
        private HashMap<V, Node<V>> nodes;
        /**
         * 记录每个节点的父节点
         */
        private HashMap<Node<V>, Node<V>> parents;
        /**
         * 记录每个根节点的大小
         */
        private HashMap<Node<V>, Integer> sizes;


        public UnionFind() {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
        }

        public UnionFind(List<V> values) {
            this();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }


        /**
         * 向并查集中添加一个元素
         *
         * @param value 元素
         */
        public void add(V value) {
            Node<V> node = nodes.get(value);
            if (node == null) {
                nodes.put(value, node);
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        /**
         * 判断两个值所在的集合是否为同一个集合
         *
         * @param one       第一个值
         * @param antherOne 第二个值
         * @return 是同一个集合返回true, 否则返回false
         */
        public boolean isSameSet(V one, V antherOne) {
            // 查找one的节点
            Node<V> oneNode = nodes.get(one);
            // 查找antherOne的节点
            Node<V> antherNode = nodes.get(antherOne);

            // 判断两个数是否都在集合中，不在直接返回false
            if (oneNode == null || antherNode == null) {
                return false;
            }
            // 查找两个节点根节点是否为同一个，是同一个则在一个集合中
            return findRoot(oneNode) == findRoot(antherNode);
        }

        /**
         * 合并两个值所在的集合
         *
         * @param one       第一个值
         * @param antherOne 第二个值
         */
        public void union(V one, V antherOne) {

            Node<V> oneNode = nodes.get(one);
            Node<V> antherNode = nodes.get(antherOne);

            // 如果传入的值所对应的节点有不存在
            if (oneNode == null || antherNode == null) {
                if (antherNode != null) {
                    oneNode = new Node<>(one);
                    nodes.put(one, oneNode);
                    Node<V> root = findRoot(antherNode);
                    parents.put(oneNode, root);
                    sizes.put(root, sizes.get(root) + 1);
                } else if (oneNode != null) {
                    antherNode = new Node<>(antherOne);
                    nodes.put(antherOne, antherNode);
                    Node<V> root = findRoot(oneNode);
                    parents.put(antherNode, root);
                    sizes.put(root, sizes.get(root) + 1);
                } else {
                    oneNode = new Node<>(one);
                    antherNode = new Node<>(antherOne);
                    nodes.put(one, oneNode);
                    nodes.put(antherOne, antherNode);
                    parents.put(oneNode, antherNode);
                    sizes.put(antherNode, 2);
                }
            } else {
                Node<V> oneRoot = findRoot(oneNode);
                Node<V> antherRoot = findRoot(antherNode);
                if (oneRoot == antherRoot) {
                    return;
                }
                Integer oneSize = sizes.get(oneRoot);
                Integer antherSize = sizes.get(antherRoot);
                Node<V> bigRoot = oneSize > antherSize ? oneRoot : antherRoot;
                Node<V> smallRoot = bigRoot == oneRoot ? antherRoot : oneRoot;
                parents.put(smallRoot, bigRoot);
                sizes.remove(smallRoot);
                sizes.put(bigRoot, oneSize + antherSize);
            }
        }

        /**
         * 返回value 所在集合的大小
         *
         * @param vales 一个元素
         * @return 元素所在结合大小
         */
        public int size(V vales) {
            Node<V> node = nodes.get(vales);
            if (node == null) {
                return 0;
            }
            return sizes.get(findRoot(node));
        }

        /**
         * 给一个节点，向上查询，返回根部节点
         *
         * @param node 一个节点
         * @return 根部节点
         */
        private Node<V> findRoot(Node<V> node) {
            // 一个辅助栈，用来临时存放找到的node往上找到的祖先节点
            Stack<Node<V>> stack = new Stack<>();
            Node<V> cur = node;
            // 如果祖先节点不是自己（不是根节点，那么继续往上找
            while (parents.get(cur) != cur) {
                // 往上找的过程，遇到的祖先节点放入辅助栈中
                stack.push(cur);
                cur = parents.get(cur);
            }
            // 将辅助栈中的节点直接连接根节点
            while (!stack.empty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }


        public int sets() {
            return sizes.size();
        }
    }

    public static void main(String[] args) {
        UnionFind<Integer> a = new UnionFind<>();
        System.out.println(a.sets());
        System.out.println(a.isSameSet(1, 2));
        a.union(1, 2);
        System.out.println(a.isSameSet(1, 2));
        a.add(1);
        a.union(2, 4);
        System.out.println(a.isSameSet(1, 4));

        a.union(9, 8);
        System.out.println(a.isSameSet(8, 9));
        System.out.println(a.sets());

    }
}
