package com.lea.algorithm.interview;

/**
 * @author lzc
 * @create 2020-11-12 20:47
 * 
 **/
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
//        print();
        System.out.println(isPowerOf2(16));
        System.out.println(isPowerOf2(10));
    }

}
