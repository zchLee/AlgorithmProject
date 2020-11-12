package com.lea.algorithm.interview;

import java.util.Stack;

/**
 * @author lzc
 * @create 2020-11-11 22:34
 * 实现一个栈，该栈带有出栈（pop） 入栈（push） 取最小值（getMin） 三个方法，要保证三个方法的时间复杂度都是O(1)
 *
 * 先复习下什么是 栈
 *  只有一个出口（入口），添加元素时，先进来的放在栈底，最上面的是栈顶，出栈时，栈顶出栈，遵循后入先出的原则
 */
public class V2 {

    public static void main(String[] args) {
        MyMinStack minStack = new MyMinStack();
        minStack.push(4);
        minStack.push(9);
        minStack.push(7);
        minStack.push(3);
        minStack.push(8);
        minStack.push(5);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        System.out.println(minStack.getMin());
        System.out.println(minStack.getMin());
    }

    public static class MyMinStack {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minStack = new Stack<>();


        // 入栈
        public void push(Integer value) {
            stack.push(value);
            if (minStack.isEmpty() || value < minStack.peek()) {
                minStack.push(value);
            }
        }

        // 出栈
        public Integer pop() {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            return stack.pop();
        }

        // 取最小值
        public Integer getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }
    }
}
