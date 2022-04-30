package com.ixiaoyu2.primary.class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author :Administrator
 * @date :2022/4/13 0013
 */
public class Code03_NumberOfIsland {
    //给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，
    //返回matrix中岛的数量

    public static int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void infect(char[][] arr, int i, int j) {
        if (i < 0 || i == arr.length || j < 0 || j == arr[0].length || arr[i][j] != '1') {
            return;
        }
        arr[i][j] = 0;
        infect(arr, i - 1, j);
        infect(arr, i + 1, j);
        infect(arr, i, j - 1);
        infect(arr, i, j + 1);
    }

    public static int numIslands2(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        Dot[][] dots = new Dot[r][c];
        List<Dot> list = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    list.add(dots[i][j]);
                }
            }
        }

        UnionSet1<Dot> unionSet = new UnionSet1<>(list);
        for (int i = 1; i < c; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionSet.union(dots[0][i - 1], dots[0][i]);
            }
        }

        for (int i = 1; i < r; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionSet.union(dots[i][0], dots[i - 1][0]);
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionSet.union(dots[i - 1][j], dots[i][j]);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionSet.union(dots[i][j - 1], dots[i][j]);
                    }
                }
            }
        }
        return unionSet.sets();
    }

    private static class Dot {
    }

    private static class Node<V> {
        V val;

        public Node(V val) {
            this.val = val;
        }
    }

    private static class UnionSet1<V> {
        private final HashMap<Node<V>, Node<V>> parents;
        private final HashMap<V, Node<V>> nodes;

        private final HashMap<Node<V>, Integer> sizeMap;

        public UnionSet1(List<V> list) {
            parents = new HashMap<>();
            nodes = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : list) {
                Node<V> node = new Node<>(v);
                parents.put(node, node);
                nodes.put(v, node);
                sizeMap.put(node, 1);
            }
        }

        private Node<V> findRoot(Node<V> v) {
            Stack<Node<V>> stack = new Stack<>();
            Node<V> cur = v;
            while (parents.get(cur) != cur) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.empty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(V one, V anotherOne) {
            Node<V> oneRoot = findRoot(nodes.get(one));
            Node<V> anotherOneRoot = findRoot(nodes.get(anotherOne));
            if (oneRoot != anotherOneRoot) {
                int oneSize = sizeMap.get(oneRoot);
                int anotherSize = sizeMap.get(anotherOneRoot);
                Node<V> big = oneSize > anotherSize ? oneRoot : anotherOneRoot;
                Node<V> small = big == oneRoot ? anotherOneRoot : oneRoot;
                parents.put(small, big);
                sizeMap.put(big, oneSize + anotherSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }


    public static int numIslands3(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionSet2 uf = new UnionSet2(board);
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.sets();
    }


    private static class UnionSet2 {
        private final int[] parent;
        private final int[] size;
        private final int[] help;
        private int sets;
        private final int col;

        public UnionSet2(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }


        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) { //i=2
            int hi = 0;
            while (i != parent[i]) { // parent[2]=1  parent[1]=3
                help[hi++] = i; // help[0] = 2 help[1]=3
                i = parent[i]; // i = 1 i = 3
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }


        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }


    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return ans;
    }

    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }

    public static void printMatrix(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        for (char[] chars : matrix) {
            for (char oneChar : chars) {
                System.out.print(oneChar + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;
/*
        row = 1000;
        col = 1000;
        board1 = generateRandomMatrix(row, col);
        board2 = copy(board1);
        board3 = copy(board1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands1(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + numIslands2(board2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands3(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        System.out.println();

        row = 10000;
        col = 10000;
        board1 = generateRandomMatrix(row, col);
        board3 = copy(board1);
        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands1(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands3(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

*/
        row = 3;
        col = 3;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            board1 = generateRandomMatrix(row, col);
            board3 = copy(board1);
            int ans1 = numIslands1(board1);
            int ans2 = numIslands3(board3);
            if (ans1 != ans2) {
                System.out.println("出错啦");
                printMatrix(board3);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
    }
}
