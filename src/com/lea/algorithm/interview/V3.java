package com.lea.algorithm.interview;

/**
 * @author lzc
 * @create 2020-11-12 20:47
 * 求出两个整数的最大公约数，要尽量优化算法性能
 * 解题思路：
 *     先来点百度得来的知识
 *      两个正整数的最大公约数是常见的数学问题，中国古代数学专著《九章算术》中便记载了求两个正整数最大公约数的一种方法﹣﹣更相减损术，
 *      术曰：“可半者半之，不可半者，副置分母、子之数，以少成多，更相减损，求其等也．以等数约之”，
 *      意思是，要求两个正整数的最大公约数，先用较大的数减去较小的数，得到差，然后用减数与差中的较大数减去较小数，
 *      以此类推，当减数与差相等时，此时的差（或减数）即为这两个正整数的最大公约数．
 *
 *     第二种方法：辗转相除法（欧几里德算法）
 *          这个算法基于一个定理：两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数
 *
 *     第三种解法：更相减损术结合辗转相除法 通过位移来计算
 *     先学习下面的知识
 *     & 按位与运算： 两个数的二进制数 按位对齐，当两个位都是 1 时，结果为 1 ，否则为 0；
 *     | 按位或运算： 两个数的二进制 按位对齐， 只要有一个位是 1，结果就是1，否则为 0
 *     ^ 异或运算：  两个位不同就得1
 *     << 左移运算： 将一个数的各二进制位，全部往左移n位（左边二进制位丢弃，右边补0）
 *          例：a = a<<2; 将a左移2位，右边补0
 *          左移1位后a = a*2;
 *          若左移后舍弃的高位不包含1，则每移动一位，相当于该数 乘以 一个 2；
 *     >> 右移运算：将一个数的各二进制全部右移若干位，整数左补0，负数左补1，右边丢弃
 *          操作数每右移一位，相当于该数除以一个2
 *          例如：a=a>>2; a=a/2/2;
 *          左补0还是1，取决于这个数是整数还是负数，整数补0，负数补1
 *
 *      这两种解法都用到了递归，道理想通
 */
public class V3 {

    // 1.更相减损术（有个缺点，当a = 10000，b=1时 要循环9999次）
    public static int getGreatestCommonDivisor1(int a, int b) {
        int big = a > b?a:b;
        int small = a < b?a:b;
        int diff = big - small;
        if (diff == small)
            return diff;
        return getGreatestCommonDivisor1(small, diff);
    };

    // 2.辗转相除法
    public static int getGreatestCommonDivisor2(int a, int b) {
        int big = a > b?a:b;
        int small = a < b?a:b;
        int remain = big % small;
        if (remain == 0)
            return remain;
        return getGreatestCommonDivisor1(small, remain);
    };

    // 3. 结合更相减损术与辗转相除法
    public static int getGreatestCommonDivisor3(int a, int b) {
        if (a == b)
            return a;
        // n & 1 运算： 是xxxxxxx 与 00000001 求& 与运算  如果n是偶数 最后的0
        if((a&1)==0 && (b&1)==0) {
            return getGreatestCommonDivisor3(a>>1,b>>1);
        } else if(!((a&1)==0 ) && (b&1)==0) {
            return getGreatestCommonDivisor3(a,b>>1);
        } else if((a&1)==0 && !((b&1)==0)) {
            return getGreatestCommonDivisor3(a>>1,b);
        } else {
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return getGreatestCommonDivisor3(small, big - small);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor1(1001, 1000));
        System.out.println(getGreatestCommonDivisor2(1001, 1000));
        System.out.println(getGreatestCommonDivisor3(1001, 1000));
    }

}
