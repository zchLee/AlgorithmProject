package com.lea.algorithm.interview;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-17 21:51
 * 给出一个整数，从该整数中去掉k个数字，要求剩下的数字形成的新整数尽可能的小
 *  其中整数的长度大于或等于k，给出的整数大小可以超过long整数类型的数字范围
 *
 *  解题思路：
 *      先分解下，
 *      如果只要求删去1个数字，一个数怎么变得竟可能的小？
 *      从高位开始，无论删除哪一位的数，位数都减小了。比如12346578，删哪位呢？
 *      删1？ 2346578，不如123657
 *      删2？ 1346578，不如123657小
 *      看到这得时候，我也以为我懂了， 删最大的
 *      删8？ 1234657，不如123578小
 *      到这就容易看出：
 *          把原整数的所有数字从左到右进行比较，如果发现某一位数字大于它右边的数字，那么删除该数字后，必然会使该数位的值降低
 *
 *      从12346578中得到 1234578 后，1234578 取出一个数怎么最小，一直取... 依次求得局部最优解，最终获得全局最优解的思想
 *      叫做贪心算法
 */
public class V9 {

    /**
     * TODO
     * @author: lzc
     * @date: 2020-11-19 21:22
     * @param num       原整数
     * @param k         删除数量
     * @return: java.lang.String
     */
    public static String removeKDigits(String num, int k) {
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            for (int j = 0; j < num.length() - 1; j++) {
                if (num.charAt(j) > num.charAt(j + 1)) {
                    num = num.substring(0, j);
                    num = num.substring(j + 1, num.length());
                    hasCut = true;
                    break;
                }
            }

            // 没有删除的数字时？
            if (!hasCut) {
                num = num.substring(0, num.length() - 1);
            }

            // 整数左侧有0？
            int start = 0;
            for(int j = 0;j < num.length() - 1; j++) {
                if (num.charAt(j) != '0')
                    break;
                start ++;
            }
            num = num.substring(start, num.length());

            // 如果所有的数字都被删除了？
            if (num.length() == 0)
                return "0";
        }
        return num;
    }

    // 优化
    public static String removeKDigits2(String num, int k) {
        // 返回的新字符串的长度
        int newLength = num.length() - k;
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // 删除数量没完，前一个数大于现在这个数，第一次放过不执行此循环操作
            while (top > 0 && k > 0 && stack[top - 1] > c) {
                top -= 1;   // 下标减一，替换上一个数
                k -= 1;     // 减少删除数量
            }
            stack[top++] = c;
        }

        // 取出stack中 第一个非0的下标
        int offset = 0;
        while(offset < stack.length && stack[offset] == '0') {
            offset++;
        }
        return newLength == offset ? "0" : new String(stack, offset,newLength);
    }

    public static void main(String[] args) {
        String s = removeKDigits("1234567", 2);
        System.out.println(s);
        String s1 = removeKDigits2("541270936", 4);
        System.out.println(s1);
    }

}
