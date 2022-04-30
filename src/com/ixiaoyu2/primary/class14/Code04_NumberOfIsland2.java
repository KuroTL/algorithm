package com.ixiaoyu2.primary.class14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/4/13 0013
 */
public class Code04_NumberOfIsland2 {

    //由m行和n列组成的二维网格图最初充满了水。我们可以执行一个addLand操作，将位置(row, col)的水变成陆地。
    // 给定要操作的位置列表，计算每个addLand操作后的岛屿数量。岛屿被水包围，通过水平或垂直连接相邻的陆地而形成。你可以假设网格的四边都被水包围着。

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {

        if (m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return null;
        }
        List<Integer> ans = new ArrayList<>(positions.length);
        UnionSet unionSet = new UnionSet(m, n);
        for (int[] position : positions) {
            if (position[0] < 0 || position[0] >= m || position[1] < 0 || position[1] >= n) {
                ans.add(0);
            } else {
                ans.add(unionSet.connect(position));
            }
        }
        return ans;
    }

    private static class UnionSet {
        /**
         * 当前节点的父节点
         */
        private final int[] parents;
        /**
         * 当前节点的节点数
         */
        private final int[] size;
        /**
         * 辅助数组
         */
        private final int[] help;
        /**
         * 集的数量
         */
        private int sets;

        private final int column;
        private final int row;

        public UnionSet(int m, int n) {
            int len = m * n;
            parents = new int[len];
            size = new int[len];
            help = new int[len];
            sets = 0;
            column = n;
            row = m;
        }


        private int index(int r, int c) {
            return r * column + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == column || c2 < 0 || c2 == column) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);

            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    parents[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parents[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }

        public int connect(int[] location) {
            int r = location[0];
            int c = location[1];
            int i = index(r, c);
            if (size[i] == 0) {
                parents[i] = i;
                size[i] = 1;
                sets++;
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);
                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
            }
            return sets;
        }
    }

    //-----------------
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
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

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }

    // 当m、n 很大时，position比较少时，并查集初始化占用太多资源 ，进行优化

    public static List<Integer> numIslands23(int m, int n, int[][] positions) {
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return null;
        }
        UnionSet2 unionSet2 = new UnionSet2();
        List<Integer> list = new ArrayList<>();
        for (int[] position : positions) {
            if (position[0] < 0 || position[1] < 0 || position[0] >= m || position[1] >= n) {
                list.add(0);
            } else {
                list.add(unionSet2.connect(position[0], position[1]));
            }
        }
        return list;

    }

    private static class UnionSet2 {

        private final HashMap<String, String> parents;
        private final HashMap<String, Integer> size;
        private final ArrayList<String> help;
        private int sets;

        public UnionSet2() {
            parents = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String s) {
            while (!s.equals(parents.get(s))) {
                help.add(s);
                s = parents.get(s);
            }

            for (String s1 : help) {
                parents.put(s1, s);
            }
            help.clear();
            return s;
        }

        public void union(String s1, String s2) {

            if (!parents.containsKey(s1) || !parents.containsKey(s2)) {
                return;
            }

            String f1 = find(s1);
            String f2 = find(s2);
            if (!f1.equals(f2)) {
                if (size.get(f1) > size.get(f1)) {
                    parents.put(f2, f1);
                    size.put(f1, size.get(f1) + size.get(f2));
                    size.remove(f2);
                } else {
                    parents.put(f1, f2);
                    size.put(f2, size.get(f1) + size.get(f2));
                    size.remove(f1);
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            String key = String.valueOf(r).concat("-").concat(String.valueOf(c));
            if (!parents.containsKey(key)) {
                parents.put(key, key);
                size.put(key, 1);
                sets++;

                String up = String.valueOf(r - 1).concat("-").concat(String.valueOf(c));
                String down = String.valueOf(r + 1).concat("-").concat(String.valueOf(c));
                String left = String.valueOf(r).concat("-").concat(String.valueOf(c - 1));
                String right = String.valueOf(r).concat("-").concat(String.valueOf(c + 1));
                union(key, up);
                union(key, down);
                union(key, left);
                union(key, right);
            }
            return sets;
        }
    }


    //------------------
    public static int[][] generateIsland(int m, int n, int num) {
        int[][] ans = new int[num][2];
        for (int[] an : ans) {
            int r = (int) (Math.random() * m);
            int c = (int) (Math.random() * n);
            an[0] = r;
            an[1] = c;
        }
        return ans;
    }

    private static int[][] copyArr(int[][] arr) {
        if (arr == null) {
            return null;
        }
        int[][] ans = new int[arr.length][];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new int[arr[i].length];
            System.arraycopy(arr[i], 0, ans[i], 0, ans[i].length);
        }
        return ans;
    }

    private static boolean isSame(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 != null ^ list2 != null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int m = 100;
        int n = 100;
        int num = 50;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] sample1 = generateIsland(m, n, num);
            int[][] sample2 = copyArr(sample1);
            int[][] sample3 = copyArr(sample1);
            List<Integer> ans1 = numIslands2(m, n, sample1);
            List<Integer> ans2 = numIslands21(m, n, sample2);
            List<Integer> ans3 = numIslands23(m, n, sample3);

            if (!isSame(ans1, ans2) || !isSame(ans1, ans3)) {
                System.out.println("出错");
                System.out.println("m:" + m + " n:" + n);
                for (int[] ints : sample1) {
                    System.out.print("locations:" + Arrays.toString(ints) + " ");
                }
                System.out.println();
                ans1.forEach((a) -> System.out.print(a + " "));
                System.out.println();
                ans2.forEach((a) -> System.out.print(a + " "));
                System.out.println();
                ans3.forEach((a) -> System.out.print(a + " "));
                break;
            }
        }
        System.out.println("结束");
    }

}
