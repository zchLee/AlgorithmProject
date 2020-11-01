package com.lea.datastructure.tree;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-1 09:46
 * 优先队列：
 *      不管插入队列的顺序，每次出栈的元素都是最大的，称之为 最大优先队列；
 *      每次出栈元素都是最小的 就称之为 最小优先队列
 *      和二叉堆 上浮 下浮 操作一致
 *
 *      下列代码实现是 最大优先队列
 */
public class PriorityQueue {

    private int size;

    private int[] array;

    public PriorityQueue() {
        // 初始化队列长度
        array = new int[32];
    }

    // 入栈
    public void enQueue(int key) {
        if (size >= array.length)
            resize();
        this.array[size++] = key;
        upAdjust();
    }

    public int deQueue() {
        if (size < 0)
            throw new RuntimeException("the queue si empty!");
        int top = array[0];
        array[0] = array[--size];
        downAdjust();
        return top;
    }

    private void resize() {
        // 队列扩容
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    /**
     *  在数组实现二叉堆中 可通过 公式推断 孩子节点
     *  左孩子节点 = 父节点下标 * 2 + 1;
     *  右孩子节点 = 父节点下标 * 2 + 2;
     *  上浮：将插入的最后一个元素，和父节点比较，如果比新加入元素 小，就交换位置，
     *      并拿新元素依次比较上一个节点，直到新元素比父元素小，或成为根节点
     */
    private void upAdjust() {
        // 新加入的元素
        int childIndex = size - 1;
        int parentIndex = (childIndex-1)/2;
//        boolean isOut = parentIndex > 0; // 是否超出界限
//        boolean isBigMore = array[childIndex] > array[parentIndex];  // 子元素比父元素大
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
//            System.out.println(parentIndex / 2 + " ------- " + (parentIndex - 1)/2);
            // 用父节点 求 父节点，只需要直接除2，倘若 -1 反而求出的不是直接父节点
            /*
            具体原因，父节点的兄弟节点，从左到右 分别是偶数，奇数；  偶数+1 = 奇数
            在java中 除以2 的结果都是一样的结果
             */
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    /*
    (parentIndex - 1) / 2; 的结果
    [10, 8, 9, 7, 1, 4, 6, 3, 2, 0, 11]
    [11, 10, 9, 7, 8, 4, 6, 3, 2, 0, 1]

parentIndex / 2;
    [10, 8, 9, 7, 1, 4, 6, 3, 2, 0, 11]
    [11, 10, 8, 7, 9, 4, 6, 3, 2, 0, 1]
     */

    // 最大优先队列，出列时 取出第一个即可，最后一个元素暂时占位 第一位，与孩子节点比较，比最大的孩子节点比较，比孩子节点小就交换
    private void downAdjust() {
        int parentIndex = 0;
        int temp = array[0];
        int childIndex = 1;
        while (childIndex < size) {
            // 如果右孩子的下标 没有超过 数组已有值（存在右孩子）
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++; // 下标+1，变成右孩子下标
            }
            // 元素大于任意 孩子节点 跳出循环
            if (temp > array[childIndex])
                break;

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex + 1;
        }
        array[childIndex] = temp;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.enQueue(3);
        queue.enQueue(5);
        queue.enQueue(10);
        queue.enQueue(7);
        queue.enQueue(2);
        System.out.println(Arrays.toString(queue.array));
        System.out.println("出队元素" + queue.deQueue());
        System.out.println("出队元素" + queue.deQueue());
    }
}
