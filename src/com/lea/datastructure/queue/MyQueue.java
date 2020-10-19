package com.lea.datastructure.queue;

/**
 * @author lzc
 * @create 2020-10-19 21:26
 * 用数组实现为了方便入队的操作，把队尾的位置规定为最后入队元素的下一个位置
 *
 * 如果你打印过，那你可能会看出一个问题，队列出栈后，左边的客供件失去了作用，这怎么将他利用起来呢？
 *  --循环队列--
 */
public class MyQueue {

    private int[] queue;
    private int front;
    private int rear;

    public MyQueue(int capacity) {
        queue = new int[capacity];
    }

    // 入队
    public void enqueue(int e) {
        if (rear >= queue.length)
            throw new RuntimeException("队列满了");
        // 队列是空的
        queue[rear] = e;
        rear++;
    }

    // 出队
    public int dequeue() {
        if (rear == front)
            throw new RuntimeException("队列空了");
        int e = queue[front];
        queue[front] = 0;
        front++;
        return e;
    }

    public void print() {
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(5);
        queue.enqueue(8);
        queue.enqueue(9);
//        queue.enqueue(11);
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.print();
        queue.dequeue();
    }

}
