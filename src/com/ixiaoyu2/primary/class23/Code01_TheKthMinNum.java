package com.ixiaoyu2.primary.class23;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author :Administrator
 * @date :2022/5/15 0015
 */
public class Code01_TheKthMinNum {

    // 一个无序数组，找到第k小的数返回


    // 算法1：使用大根堆，时间复杂度O(n*logk)

    public static int findKthMinNum1(int[] arr, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);

        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    // 法2：改写快排，时间复杂度O(N)

    public static int findKthMinNum2(int[] arr, int k) {
        return process(copyArr(arr), 0, arr.length - 1, k - 1);
    }

    private static int[] copyArr(int[] arr) {
        return Arrays.copyOf(arr, arr.length);

    }

    private static int process(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = partition(arr, l, r, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, l, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, r, index);
        }

    }

    public static int[] partition(int[] arr, int l, int r, int pivot) {
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

    private static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }


    // 法3 bfprt算法 时间复杂度O(N)

    public static int findKthMinNum3(int[] arr, int k) {

        return bfprt(copyArr(arr), 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        // L...R  每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedian(arr, l, r);

        int[] range = partition(arr, l, r, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, l, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, r, index);
        }
    }

    /**
     * 以5位元素为一组，排序，获取每组的中点，组成新的一组，返回新组的中点
     */
    public static int medianOfMedian(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int mSzie = size / 5 + offset;
        int[] mArr = new int[mSzie];
        for (int team = 0; team < mSzie; team++) {
            int teamFirst = l + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(r, teamFirst + 4));
        }
        return bfprt(mArr, 0, mSzie - 1, mSzie >> 1);
    }


    public static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        return arr[l + ((r - l) >> 1)];
    }

    public static void insertSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = findKthMinNum1(arr, k);
            int ans2 = findKthMinNum2(arr, k);
            int ans3 = findKthMinNum3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
