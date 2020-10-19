package com.lea.datastructure.queue;

/**
 * @author lzc
 * @create 2020-10-19 21:46
 *
 * 循环队列
 *  在数组不做扩容的前提下，可以利用已出队元素留下的空间，让队尾指针重新指回数组的首位
 *  注意：队尾指针指向的位置永远空出1位，所以队列最大容量比数组长度小于1
 */
public class CycleQueue {

    private int[] queue;
    private int front;
    private int rear;

    public CycleQueue(int capacity) {
        queue = new int[capacity];
    }

//    (rear + 1)%queue.length 表示队头下标
    // 入队
    public void enqueue(int e) {
        if ((rear + 1) % queue.length == front)
            throw new RuntimeException("队列满了");

        queue[rear] = e;
        rear = (rear + 1) % queue.length;
    }

    // 出队
    public int dequeue() {
        if (rear == front)
            throw new RuntimeException("队列空了");
        int e = queue[front];
        queue[front] = 0;
        front = (front + 1) % queue.length;   // 队头的位置
        return e;
    }

    public void print() {
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue[i] + "\t");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(5);
        queue.enqueue(5);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(1);
//        queue.enqueue(11);
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.print();
        queue.dequeue();

        queue.print();
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.print();
        queue.dequeue();
        queue.print();
    }
}
