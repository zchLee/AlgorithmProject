package com.lea.algorithm.interview;

import java.util.Stack;

/**
 * @author lzc
 * @create 2020-11-17 21:51
 * 利用栈来实现队列
 *
 * 栈的特点是先入先出，出入元素都是在同一端（栈顶）
 * 队列的特点是先入先出，出入元素实在不同的两端（队头和队尾）
 * 思路：
 *      一个队列不成，就拿两个队列来组合使用
 *      1.队列A模拟入队
 *          在模拟入队时，将每个元素压入栈底
 *      2.队列B模拟出队
 *          模拟出队时，将A栈中的元素从栈顶取出，依次塞入B队列栈底
 *
 * 入队的时间复杂度是O(1).
 * 出队时，如果涉及栈A和栈B的元素迁移，那么出队的时间是O(n),反之是O(1)
 * 这种情况引出一个新概念：
 *  均摊时间复杂度：
 *      需要元素迁移的出栈操作时少数情况，并且不可能连续出现，气候的大多数出队操作都不需要
 *      元素迁移，所以把时间均摊到每一次出队操作上，其时间复杂度是O(1)
 */
public class V7 {

    public static void main(String[] args) {
        SimulationQueue queue = new SimulationQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }

    // 模拟队列
    public static class SimulationQueue {
        private Stack<Integer> stackA = new Stack<>();
        private Stack<Integer> stackB = new Stack<>();

        // 入队
        public void enQueue(Integer element) {
            stackA.push(element);
        }

        // 出队
        public Integer deQueue() {
            if (stackB.isEmpty()) {
                if (stackA.isEmpty())
                    return null;
                transfer();
            }
            return stackB.pop();
        }

        // 栈A向栈B转移数据
        public void transfer() {
            while (!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
        }
    }
}
