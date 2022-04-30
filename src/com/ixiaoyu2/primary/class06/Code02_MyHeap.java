package com.ixiaoyu2.primary.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author :Administrator
 * @Date :2022/3/12
 * @Description :com.msb.primary.class06
 * @Version: 1.0
 */
public class Code02_MyHeap {


    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        PriorityQueue<Integer> heap2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        heap2.add(2);
        heap2.add(3);
        heap2.add(1);
        heap2.add(6);
        heap2.add(3);
        System.out.println(heap2.peek());
    }
}

//大根堆
class MyHeap {
    private int[] heap;
    private int size;
    private static final int DEFAULT_INITIAL_LENGTH = 16;
    private int limit;

    public MyHeap() {
        heap = new int[DEFAULT_INITIAL_LENGTH];
        limit = DEFAULT_INITIAL_LENGTH;
        size = 0;
    }

    public MyHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }

    public void push(int value) throws RuntimeException {
        if (size < limit) {
            heap[size] = value;
            heapInsert(heap, size++);
        } else {
            throw new RuntimeException("index out of bounds");
        }
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("heap is null");
        }
        int ans = heap[0];
        swap(heap, 0, --size);
        heapify(heap, 0, size);
        return ans;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("heap is null");
        }
        return heap[0];
    }

    //向上调整
    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //向下调整
    private void heapify(int[] arr, int index, int heapSize) {
        while ((2 * index + 1) < heapSize) {
            int large = 2 * index + 2 < heapSize && arr[2 * index + 2] > arr[2 * index + 1] ? (2 * index + 2) : (2 * index + 1);
            large = arr[large] > arr[index] ? large : index;
            if (large == index) {
                break;
            }
            swap(arr, index, large);
            index = large;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String toString() {
        return "MyHeap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }
}