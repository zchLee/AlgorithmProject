package com.lea.datastructure.linkedlist;

/**
 * @author lzc
 * @create 2020-10-18 20:57
 *
 * 单向链表实现
 */
public class SingleLinked {
    // (依赖头节点，找到链表其他数据)
    private Node head;      // 头节点指针
    private Node last;      // 尾部指针
    private int size;       // 链表实际长度

    // 查找，在链表中，只能从头部开始一个一个节点逐一查找，最坏的时间复杂度是O(n)
    public Node get(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("超出链表范围");
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 插入节点，如果内存足够，链表可以无限大
    /**
     * @param data      链表数据
     * @param index     插入位置
     * @return: void
     */
    public void insert(int data, int index) {
        if (index < 0 || index > size)
            throw new RuntimeException("超出链表范围");
        Node insertNode = new Node();
        insertNode.data = data;
        if (size == 0) {
            // 空链表，链表头就是他
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {
            // 插入头部
            head = insertNode;
            insertNode.next = head;
        } else if (size == index) {
            // 尾部插入
            last.next = insertNode;
            last = insertNode;
        } else {
            // 中间插入
            Node prevNode = get(index - 1);
            insertNode.next = prevNode.next;
            prevNode.next = insertNode;
        }
        size++;
    }

    // 删除节点
    public Node remvoe(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("超出链表范围");
        Node removeNode = null;
        if (index == size) {
            // 尾部删除
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        } else if (index == 0) {
            // 头部删除
            removeNode = head;
            head = head.next;
        } else {
            // 中间删除
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = removeNode.next;
        }
        return removeNode;
    }

    // 输出
    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    // 静态内部类，给链表存放数据
    private static class Node {
        // 本身存放的数据
        int data;
        // 指针，指向下一个节点
        Node next;
    }


    public static void main(String[] args) {
        SingleLinked linked = new SingleLinked();
        linked.insert(1, 0);
        linked.insert(3, 1);
        linked.insert(5, 2);
        linked.insert(7, 3);
        linked.insert(9, 4);
        linked.insert(11, 5);
        linked.print();

        linked.remvoe(5);
        linked.print();
    }
}
