package com.lea.datastructure.stack;

import java.awt.print.PrinterGraphics;

/**
 * @author lzc
 * @create 2020-10-19 21:03
 * 自定义栈
 */
public class MyStack {

    private int[] stack;
    private int top;     // 栈顶


    public MyStack(int capacity) {
        stack = new int[capacity];
    }

    // 入栈
    public void push(int e) {
        if (top > stack.length)
            throw new RuntimeException("栈满了");
        if (top <= 0) {
            // 空栈
            stack[0] = e;
        } else {
            stack[top] = e;
        }
        top++;
    }

    // 出栈
    public int pop() {
        int e = stack[top - 1];
        stack[top - 1] = 0;
        top--;
        return e;
    }

    public void print() {
        for (int i = 0; i < stack.length; i++) {
            System.out.print(stack[i] + "\t");
        }
        System.out.println();
    }

}
