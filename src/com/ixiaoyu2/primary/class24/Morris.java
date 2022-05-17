package com.ixiaoyu2.primary.class24;

/**
 * Morris变遍历
 *
 * @author :Administrator
 * @date :2022/5/17 0017
 */
public class Morris {

    private static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    // 递归遍历  先序 头-左-右

    public static void preProcess(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preProcess(head.left);
        preProcess(head.right);
    }

    // 递归遍历  中序 左-头-右

    public static void inProcess(Node head) {
        if (head == null) {
            return;
        }
        inProcess(head.left);
        System.out.print(head.value + " ");
        inProcess(head.right);
    }

    // 递归遍历  后序 左-右-头

    public static void posProcess(Node head) {
        if (head == null) {
            return;
        }
        posProcess(head.left);
        posProcess(head.right);
        System.out.print(head.value + " ");
    }

    // 按层遍历

    public static void layerProcess(Node head) {
        if (head == null) {
            return;
        }
        Node[] queue = new Node[100];
        int inCursor = 0;
        int outCursor = 0;
        int count = 0;
        queue[inCursor++] = head;
        count++;
        while (count != 0) {
            inCursor = inCursor == 10 ? 0 : inCursor;
            outCursor = outCursor == 10 ? 0 : outCursor;
            Node cur = queue[outCursor++];
            count--;
            if (cur.left != null) {
                queue[inCursor++] = cur.left;
                count++;
            }
            if (cur.right != null) {
                queue[inCursor++] = cur.right;
                count++;
            }
            System.out.print(cur.value + " ");
        }
    }


    // Moris遍历

    /**
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1 如果cur没有左孩子，cur向右移动(cur = cur.right)
     * 2 如果cur有左孩子，找到左子树上最右的节点mostRight：
     * a 如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)
     * b 如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)
     * 3 cur为空时遍历停止
     */

    public static void morris(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    // morris 遍历实现先序

    public static void preMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
    }


    // morris 遍历实现中序

    public static void inMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
    }

    // Morris 遍历实现后序

    public static void posMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
    }

    private static void printEdge(Node head) {
        Node tail = reverse(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverse(tail);
    }

    private static Node reverse(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.left = new Node(3);
        head.right = new Node(1);
        head.left.left = new Node(5);
        head.left.right = new Node(6);
        head.right.left = new Node(8);
        head.right.right = new Node(9);

        System.out.println("先序");
        preProcess(head);
        System.out.println();
        System.out.println("中序");
        inProcess(head);
        System.out.println();
        System.out.println("后序");
        posProcess(head);
        System.out.println();
        System.out.println("按层");
        layerProcess(head);
        System.out.println();
        System.out.println("morris 实现先序");
        preMorris(head);
        System.out.println();
        System.out.println("morris 实现中序");
        inMorris(head);
        System.out.println();
        System.out.println("morris 实现后序");
        posMorris(head);
    }
}
