package com.lea.algorithm.interview;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lzc
 * @create 2020-11-17 21:51
 * 给出一个正整数，找出这个正整数所有数字全排列的下一个数
 * 通俗点就是 在一个整数所包含数字的全部组合中，找到一个大于且仅大于原数的新整数
 * 思路：
 *      一个数顺序排列的时候最小，逆序排列的时候最大
 *      为了和原数接近，尽量保持高位不变，低位在最小的范围内变换顺序，
 *      1、至于变换顺序的大小，取决于当前整数的逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 *      2. 让逆序区域的前一位和逆序区域中大于他的最小的数字交换位置
 *      3. 把原来的逆序区转为顺序状态
 */
public class V8 {

    public static void main(String[] args) {
        // 15432
        int[] numbers = {1,5,4,3,2};
        int[] num = findNearestNum(numbers);
        print(num);
    }

    public static int[] findNearestNum(int[] number) {
        // 1.从后向前看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        int index = findTransferPoint(number);
        int[] numberCopy = Arrays.copyOf(number, number.length);
        // 2
        exchangeHead(numberCopy, index);
        // 3
        reverse(numberCopy, index);
        return numberCopy;
    }

    // 找到数字置换的边界, 返回的下标的值是逆序区域中最大的值
    public static int findTransferPoint(int[] number) {
        for (int i = number.length - 1; i > 0; i--) {
            if (number[i] > number[i - 1])
                return i;
        }
        return 0;
    }

    // 让逆序区域的前一位和逆序区域中大于它的最小数字交换位置
    public static void exchangeHead(int[] numbers, int index) {
        // 逆序区域的前一位
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
    }

    // 把交换后的逆序区域转换成顺序区域
    public static void reverse(int[] nums, int index) {
        for (int i = index,j = nums.length - 1; i < j; i++,j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void print(int[] nums) {
        System.out.println(Arrays.toString(nums).replace(", ", "").replace("[", "").replace("]", ""));
    }

}
