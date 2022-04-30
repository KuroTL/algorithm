package com.ixiaoyu2.rookie.class04;

/**
 * @Author :Administrator
 * @Date :2022/2/20
 * @Description :com.msb.rookie.class04
 * @Version: 1.0
 */
public class MyLinkedList<E> {
    /**
     * 单链表结构
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node<E> node;
    private Node<E> head;
    private Node<E> last;

    /**
     * 构造函数进行初始化
     */
    public MyLinkedList() {
        size = 0;
        node = null;
        head = null;
        last = null;
    }

    public int size() {
        return size;
    }

    /**
     * 添加元素
     *
     * @param e 添加的E类型的元素
     */
    public void add(E e) {
        //先将传入的数据对象封装为Node对象
        node = new Node<>(e, null);
        if (size == 0) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    /**
     * 查找头结点的元素
     *
     * @return 头结点的元素
     */
    public E getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    /**
     * 删除头结点的元素
     *
     * @return 删除头结点的元素
     */
    public E removeFirst() throws RuntimeException {
        if (head == null) {
            throw new RuntimeException("链表为空，没有头结点元素");
        }
        E e = head.data;
        head = head.next;
        size--;
        return e;
    }

    /**
     * 返回最后一个节点的元素
     *
     * @return 返回最后一个节点的元素
     */
    public E getLast() {
        if (last == null) {
            return null;
        }
        return last.data;
    }

    /**
     * 删除最后一个节点的元素，因为是单链表实现，所以需要遍历链表
     *
     * @return
     */
    public E removeLast() {
        if (last == null) {
            throw new RuntimeException("链表为空，没有尾结点元素");
        }
        if (null != head && head == last) {
            E e = last.data;
            head = null;
            last = null;
        }
        E e = last.data;
        Node<E> pre = head;
        while (pre.next != last) {
            pre = pre.next;
        }
        pre.next = null;
        size--;
        last = pre;
        return e;
    }

    @Override
    public String toString() {
        Node<E> cur = head;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(cur.data);
            s.append(" ");
            cur = cur.next;
        }
        return s.toString();
    }
}

class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        System.out.println(list.getFirst() + "  size:" + list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);
        System.out.println(list.getFirst() + "  size:" + list.size());
        System.out.println(list.getLast() + " size:" + list.size());
        System.out.println(list.removeFirst() + " first:" + list.getFirst() + " size:" + list.size());
        System.out.println(list.removeLast() + " last:" + list.getLast() + " size:" + list.size());
        System.out.println(list);

    }
}
