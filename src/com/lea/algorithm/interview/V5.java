package com.lea.algorithm.interview;

/**
 * @author lzc
 * @create 2020-11-12 20:47
 * 求出两个整数的最大公约数，要尽量优化算法性能
 * 判断一个整数是否是2的整数次幂
 * 思路：
 *  2的整数次幂的数都有一个特点，十进制不好看，看二进制
    4的二进制：100	原数减一的二进制：11
    8的二进制：1000	原数减一的二进制：111
    16的二进制：10000	原数减一的二进制：1111
    32的二进制：100000	原数减一的二进制：11111
    64的二进制：1000000	原数减一的二进制：111111
 * 全都是1开头其他都是0，原数减一的值都是1，这两个二进制 做 &运算会怎样呢？
 * 100 & 11
 *      100
 *      011
 *     -----
 *      000
 *  结果都是0
 */
public class V5 {

    // 判断一个数是不是2的整数次幂
    public static boolean isPowerOf2(int num) {
        return (num & num-1) == 0;
    }


    // 2的整数次幂，转成二进制，该数-1的二进制的样子
    public static void print() {
        // 求出2的1到15此幂
        int num = 2;
        for (int i = 1; i <= 15; i++) {
            num *= 2;
            System.out.println(num + "的二进制：" + Integer.toBinaryString(num) + "\t原数减一的二进制：" + Integer.toBinaryString(num - 1));
        }
    }

    public static void main(String[] args) {

    }

}
