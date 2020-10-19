package com.lea.datastructure.stack;

/**
 * @author lzc
 * @create 2020-10-19 20:56
 * 前面学的数组和链表都是物理结构的，而物理结构的是在内存中试试存在的存储结构
 * 还有中结构是逻辑结构：是抽象概念，依赖屋里结构的存在
 *              线性结构          非线性结构
 * 逻辑结构： 顺序表、栈、队列   |   树、图
 *              顺序存储结构      链式存储结构
 * 物理结构：    数组          |  链表
 *
 * 什么是栈？
 *  栈只有一端是开的，添加元素也是从此端进入，压倒最底下，直到压满。出栈也是从此端出来，
 *  遵循后进先出（First In Last Out, FILO）的规则
 *  最早进入的元素存放的位置称作栈低(bottom)，最后进入的位置叫做栈顶（Top）
 */
public class Main {

    public static void main(String[] args) {
        MyStack stack = new MyStack(5);
        stack.push(2);
        stack.push(47);
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.print();

        int pop0 = stack.pop();
        int pop1 = stack.pop();
        stack.print();
        int pop2 = stack.pop();
        int pop3 = stack.pop();
        int pop4 = stack.pop();
        stack.print();
    }
}
