package com.ixiaoyu2.primary.class23;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/5/15 0015
 */
public class Code02_TopKNum {

    // 给定一个无序数组arr中，长度为N，给定一个正数k，返回topk个最大的数

    //排序+收集 时间复杂父 O(n*logN)

    public static int[] getTopK1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        k = Math.min(n, k);
        Arrays.sort(arr);
        int[] res = new int[k];

        for (int i = n - 1, j = 0; j < k; i--, j++) {
            res[j] = arr[i];
        }
        return res;
    }

    // 使用了堆结构，时间复杂度O(n+K*logn)

    public static int[] getTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;

        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }

        int heapSize = n;

        int count = 0;
        while (heapSize > 0 && count < k) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
            count++;
        }


        k = Math.min(n, k);
        int[] res = new int[k];
        for (int i = n - 1, j = 0; j < k; i--, j++) {
            res[j] = arr[i];
        }
        return res;
    }

    private static void heapify(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        while (left < size) {
            int bigIndex = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            bigIndex = arr[i] >= arr[bigIndex] ? i : bigIndex;
            if (bigIndex == i) {
                break;
            }
            swap(arr, i, bigIndex);
            i = bigIndex;
            left = 2 * i + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 时间复杂度O(N+k*logk)
    public static int[] getTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        k = Math.min(k, n);

        int num = minK(arr, n - k);
        int[] res = new int[k];

        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > num) {
                res[index++] = arr[i];
            }
        }

        for (; index < k; index++) {
            res[index] = num;
        }
        Arrays.sort(res);
        for (int l = 0, r = k - 1; l < r; l++, r--) {
            swap(res, l, r);
        }
        return res;
    }

    private static int minK(int[] arr, int i) {

        return bfprt(Arrays.copyOf(arr, arr.length), i - 1);

    }

    private static int bfprt(int[] arr, int index) {

        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int pivot = medianOfMedian(arr, l, r);
            int[] range = partition(arr, l, r, pivot);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r = range[0] - 1;
            } else {
                l = range[1] + 1;
            }
        }
        return arr[l];

    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static int medianOfMedian(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int mSzie = size / 5 + offset;
        int[] mArr = new int[mSzie];
        for (int team = 0; team < mSzie; team++) {
            int teamFirst = l + team * 5;
            mArr[team] = median(arr, teamFirst, Math.min(r, teamFirst + 4));
        }
        return mArr[mSzie >> 1];
    }

    private static int median(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        return arr[l + ((r - l) >> 1)];
    }

    private static void insertSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l && arr[j + 1] < arr[j]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 生成随机数组测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean pass = true;
        System.out.println("测试开始，没有打印出错信息说明测试通过");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = generateRandomArray(maxSize, maxValue);

            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);

            int[] ans1 = getTopK1(arr1, k);
            int[] ans2 = getTopK2(arr2, k);
            int[] ans3 = getTopK3(arr3, k);
            if (!isEqual(ans1, ans2) || !isEqual(ans1, ans3)) {
                pass = false;
                System.out.println("出错了！");
                printArray(ans1);
                printArray(ans2);
                printArray(ans3);
                break;
            }
        }
        System.out.println("测试结束了，测试了" + testTime + "组，是否所有测试用例都通过？" + (pass ? "是" : "否"));
    }

}
