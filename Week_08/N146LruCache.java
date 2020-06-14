package cn.lihua.week08;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

 进阶:

 你是否可以在 O(1) 时间复杂度内完成这两种操作？

 示例:

 LRUCache cache = new LRUCache( 2 ); // 2: 缓存容量

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

 */
public class N146LruCache {

    class LRUCache {
        class Node{
            private Node next, pre;
            private int key, val;
            public Node(int key, int val) {
                this.val = val;
                this.key = key;
            }
        }

        private class DoubleList {
            private Node head, tail;    // 头,尾虚节点
            private int size;           // 链表元素数
            public DoubleList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.pre = head;
                size = 0;
            }

            // 在链表头部添加节点 x
            public void addFirst(Node x) {
                x.next = head.next;
                x.pre = head;
                head.next.pre = x;
                head.next = x;
                size++;
            }

            // 删除链表中的 x 节点（x 一定存在）
            public void remove(Node x) {
                x.next.pre = x.pre;
                x.pre.next = x.next;
                size--;
            }

            // 删除链表中最后一个节点，并返回该节点
            public Node removeLast() {
                if (tail.pre == head) return null;
                Node last = tail.pre;
                remove(last);
                return last;
            }

            // 返回链表长度
            public int size() {
                return size;
            }
        }

        private HashMap<Integer, Node> map;
        private DoubleList cache;
        private int cap;    // 最大容量

        public LRUCache(int capacity) {
            map = new HashMap<>();
            cache = new DoubleList();
            this.cap = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            int val = map.get(key).val;
            // 将数据提前
            put(key, val);
            return val;
        }

        public void put(int key, int value) {
            // 先把新节点 x 做出来
            Node x = new Node(key, value);
            if (map.containsKey(key)) {
                cache.remove(map.get(key));
                cache.addFirst(x);
                map.put(key, x);    // 更新map中对应的数据
            } else {
                if (cap == cache.size()) {
                    // 删除最后一个节点
                    Node last = cache.removeLast();
                    map.remove(last.key);   // 这里决定了Node节点中,需要放key
                }
                // 直接添加到头部
                cache.addFirst(x);
                map.put(key, x);
            }
        }


    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
