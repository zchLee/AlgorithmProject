package com.lea.algorithm.a1sort.interview;

import java.util.HashMap;

/**
 * @author lzc
 * @create 2020-11-11 19:57
 * 有个单向链表，链表中有可能出现环，如何判断链表是有环链表
 */
public class V1 {

    // 1. 利用hash表  空间复杂度大了
    public static boolean isCycle(Node node) {
        HashMap<Node, Integer> map = new HashMap<>();
        if (map.containsKey(node))
            return true;
        else
            map.put(node,0);
        while (node.next != null) {
            if (map.containsKey(node))
                return true;
            else
                map.put(node,0);
        }
        return false;
    }

    // 2. 利用两个指针，一个走一步，另一个指针走两步，当两个指针相遇时就是有环，类似数学中的追及问题
    public static boolean isCycle2(Node node) {
        Node p1 = node;
        Node p2 = node;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2)
                return true;
        }
        return false;
    }

    // 扩展1，求如果有环，求环的长度
    public static int cycleLength(Node node) {
        Node p1 = node;
        Node p2 = node;
        int times = 0;
        int length = 0;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                times++;
            }
            if (times == 1)
                length++;
            if (times == 2)
                return length;
        }
        return 0;
    }

    // 扩展1 解题2
    public static int cycleLength2(Node node) {
        Node p1 = node;
        Node p2 = node;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                int len = 1;
                p1 = p1.next;
                p2 = p2.next.next;
                while (p1 != p2) {
                    len++;
                    p1 = p1.next;
                    p2 = p2.next.next;
                }
                return len;
            }
        }
        return 0;
    }

    // 扩展2 求入环点
    public static int cycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                p1 = head;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1.value;
            }
        }
        return 0;
    }

    public static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
//      求单向链表是否有环
//        System.out.println(isCycle2(head));
        // 求单向链表 环长度
//        System.out.println(cycleLength(head));
//        System.out.println(cycleLength2(head));
        // 求入环点
        System.out.println(cycle(head));
    }
}
