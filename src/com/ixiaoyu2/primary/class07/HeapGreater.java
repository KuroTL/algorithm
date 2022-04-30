package com.ixiaoyu2.primary.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/3/15 0015
 * @description :com.msb.primary.class07
 * @version: 1.0
 */
public class HeapGreater<T> {
    /**
     * 用户传入的比较器
     */
    private final Comparator<? super T> comparator;
    /**
     * 底层用ArrayList动态数组
     */
    private ArrayList<T> heap;
    /**
     * 反向序列表
     */
    private HashMap<T, Integer> indexMap;
    /**
     * 堆大小
     */
    private int heapSize;

    /**
     * 构造器
     *
     * @param comparator 用户实现的比较器
     */
    public HeapGreater(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    /**
     * 堆是否为空
     *
     * @return 为空返回true, 不为空返回false
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 堆大小
     *
     * @return heapSize
     */
    public int size() {
        return heapSize;
    }

    /**
     * 堆是否包含某个元素
     *
     * @param obj 元素
     * @return 包含返回true, 不包含返回false
     */
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    /**
     * 查看堆顶元素
     *
     * @return 堆顶元素
     */
    public T peek() {
        return heap.get(0);
    }

    /**
     * 添加元素
     *
     * @param obj 元素
     */
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 向上调整堆结构
     *
     * @param index 当前元素下标
     */
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 取出堆顶元素
     *
     * @return 堆顶元素
     */
    public T pop() {
        T ans = heap.get(0);
        heap.set(0, heap.get(heapSize - 1));
        indexMap.remove(ans);
        indexMap.put(heap.get(0), 0);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    /**
     * 删除某个元素
     *
     * @param obj 元素
     */
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (replace != obj) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    /**
     * 从某个元素开始调整堆结构
     *
     * @param obj 元素
     */
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    /**
     * 返回所有堆元素
     *
     * @return 封装堆元素的List集合
     */
    public List<T> getAllElements() {
        return new ArrayList<>(heap);
    }

    /**
     * 向下调整堆结构
     *
     * @param index 当前元素下标
     */
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int right = left + 1;
            int best = right < heapSize && (comparator.compare(heap.get(right), heap.get(left)) < 0) ? right : left;
            best = (comparator.compare(heap.get(best), heap.get(index)) < 0) ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换堆元素
     *
     * @param i 下标i
     * @param j 下标j
     */
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
