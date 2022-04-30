package com.ixiaoyu2.primary.class01;

/**
 * @Author :Administrator
 * @Date :2022/3/2
 * @Description :com.msb.primary.class01
 * @Version: 1.0
 */
public class Code06_BinarySearchAwesome {

    /**
     * 局部最小值问题
     *
     * @param arr 随机数组，满足相邻2个数不相等
     * @return 任意一个局部最小值
     */
    public static int binarySearchAwesome(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int index = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                index = mid;
                break;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return index;
    }


    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }



    /*
     *以下为对数器用于此测试
     */

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int index = -1;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int[] generateRandomArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        if (ans.length == 0) {
            return ans;
        }
        ans[0] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        for (int i = 1; i < ans.length; i++) {
            do {
                ans[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
            } while (ans[i] == ans[i - 1]);
        }
        return ans;
    }

    public static boolean check(int[] arr, int i) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        if (i == 0 || i == arr.length - 1) {
            return true;
        }
        if (arr[i] < arr[i + 1] && arr[i] < arr[i - 1]) {
            return true;
        }
        return false;
    }

    public static void printArr(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 20;
        int testTimes = 100000;
        System.out.println("测试开始~");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            int i1 = getLessIndex(arr);
            int i2 = binarySearchAwesome(arr);
            if (check(arr, i1) ^ check(arr, i2)) {
                System.out.println("挑战失败，算法出错");
                printArr(arr);
                System.out.println(i1);
                System.out.println(i2);
                System.out.println();
                break;
            }
        }
        System.out.println("测试结束~");
    }

}
