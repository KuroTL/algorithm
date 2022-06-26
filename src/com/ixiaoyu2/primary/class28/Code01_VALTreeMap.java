package com.ixiaoyu2.primary.class28;

/**
 * AVLTree 实现有序表
 *
 * @author :Long
 * @date :2022/6/17 0017
 */
@SuppressWarnings("ALL")
public class Code01_VALTreeMap {


    public static class AVLTreeMap<K extends Comparable<K>, V> {
        /**
         * AVLTree节点
         *
         * @param <K> key,支持泛型
         * @param <V> value，支持泛型
         */
        private static class AVLNode<K extends Comparable<K>, V> {
            private final K key;
            private V value;
            private AVLNode<K, V> left;
            private AVLNode<K, V> right;
            private int height;

            public AVLNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        /**
         * 根节点
         */
        private AVLNode<K, V> root;
        /**
         * 节点数量
         */
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }


        /**
         * 添加节点
         *
         * @param key   key
         * @param value value
         */
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                // 更新节点
                lastNode.value = value;
            } else {
                // 添加节点
                size++;
                root = add(root, key, value);
            }
        }

        /**
         * 删除某个key对应的节点
         *
         * @param key key
         */
        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        /**
         * 获取key对应的值
         *
         * @param key key
         * @return V value
         */
        public V get(K key) {
            if (containsKey(key)) {
                AVLNode<K, V> lastNode = findLastIndex(key);
                if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                    return lastNode.value;
                }
            }
            return null;
        }

        /**
         * 最小节点的key
         *
         * @return K key
         */
        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        /**
         * 最大节点的key
         *
         * @return K key
         */
        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        /**
         * 小于等于某个key的最接近节点的key
         *
         * @param key key
         * @return K key
         */
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.key;
        }

        /**
         * 大于等于某个key的最接近的节点的key
         *
         * @param key key
         * @return K key
         */
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.key;
        }

        /**
         * 查询是否存在某个key
         *
         * @param key key
         * @return 存在: true<br/>不存在: false
         */
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0;
        }

        /**
         * 返回节点数量
         *
         * @return 节点数量
         */
        public int size() {
            return size;
        }

        /**
         * 找到小于等于当前key的最近的节点
         *
         * @param key key
         * @return AVLNode
         */
        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> res = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    res = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    res = cur;
                    cur = cur.right;
                }
            }
            return res;
        }

        /**
         * 找到大于等于当前key的最近的节点
         *
         * @param key key
         * @return AVLNode
         */
        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> res = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    res = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    res = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return res;
        }

        /**
         * 找最接近 key的节点
         *
         * @param key key
         * @return AVLNode
         */
        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        /**
         * 在某个节点后添加节点
         *
         * @param cur   当前头节点
         * @param key   key
         * @param value value
         * @return 新的头结点
         */
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            // 当前节点为null，表示添加第一个节点
            if (cur == null) {
                return new AVLNode<>(key, value);
            }
            // 当前值比头结点小，添加到左边
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            } else { // 当前值比头结点大，添加到左边

                cur.right = add(cur.right, key, value);
            }
            // 调整高度
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            // 进行平衡性调整，并返回新的头结点
            return maintain(cur);
        }

        /**
         * 删调cur为头结点的树，key代表的节点
         *
         * @param cur 当前头节点
         * @param key key
         * @return 新的头结点
         */
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            // 要删除的值比头结点大，那么在头结点的右边
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                // 要删除的值比头结点小，那么在头结点的左边
                cur.left = delete(cur.left, key);
            } else { //删除当前节点,当前节点是否有子树分4种情况
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left == null) {
                    cur = cur.right;
                } else if (cur.right == null) {
                    cur = cur.left;
                } else {
                    // 找到当前节点右树最左边的节点（大于当前节点且最节点当前节点）
                    AVLNode<K, V> replace = cur.right;
                    while (replace.left != null) {
                        replace = replace.left;
                    }
                    // 先将cur 右树上，replace节点删除，删除后会进行平衡性调整
                    cur.right = delete(cur.right, replace.key);
                    // 替换cur节点
                    replace.left = cur.left;
                    replace.right = cur.right;
                    cur = replace;
                }
            }
            // 调整高度和size
            if (cur != null) {
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            }
            return maintain(cur);
        }


        /**
         * 对当前节点进行右旋
         *
         * @param cur 当前节点
         * @return 新的节点
         */
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max(cur.right != null ? cur.right.height : 0, cur.left != null ? cur.left.height : 0) + 1;
            left.height = Math.max(cur.height, left.left != null ? left.left.height : 0) + 1;
            return left;
        }

        /**
         * 对当前节点进行左旋
         *
         * @param cur 当前节点
         * @return 新的节点
         */
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max(cur.right != null ? cur.right.height : 0, cur.left != null ? cur.left.height : 0) + 1;
            right.height = Math.max(cur.height, right.right != null ? right.right.height : 0) + 1;
            return right;
        }

        /**
         * 进行平衡性调整
         *
         * @param cur 当前节点
         * @return 新的头结点
         */
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left != null ? cur.left.height : 0;
            int rightHeight = cur.right != null ? cur.right.height : 0;

            // 当前节点不平衡，进行调整
            if (Math.abs(leftHeight - rightHeight) > 1) {
                // 左子树高度导致不平衡，需进行调整
                if (leftHeight > rightHeight) {
                    // 左节点的 左子树的高度
                    int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                    // 左节点 右子树的高度
                    int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;

                    // LL 或 同时LL、LR 进行右旋调整
                    if (leftLeftHeight >= leftRightHeight) {
                        rightRotate(cur);
                    } else {
                        // LR类型进行两次旋转
                        cur.left = leftRotate(cur.left);
                        cur = rightRotate(cur);
                    }
                } else {
                    // 右节点的 左子树的高度
                    int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                    // 右节点 右子树的高度
                    int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;

                    // RR 或 同时RR、RL 进行左旋调整
                    if (rightRightHeight >= rightLeftHeight) {
                        leftRotate(cur);
                    } else {
                        // RL类型进行两次旋转
                        cur.right = rightRotate(cur.right);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }
    }
}
