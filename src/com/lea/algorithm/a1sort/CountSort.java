package com.lea.algorithm.a1sort;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-9 20:56
 * 计数排序：
 *  在特殊情况下，数组的取值范围确定，可以利用数组下标来确定元素的正确位置
 */
public class CountSort {

    public static int[] countSort(int[] arr) {
        // 1、取出数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 2、构建计数数组，初始化是0
        int[] countArr = new int[max + 1];
        // 3、遍历数列，填充计数数组
        for (int i = 0; i < arr.length; i++) {
//            arr[i] 表示 这个数，在计数数组中的位置，第一次都是0
            countArr[arr[i]]++;
        }
        // 4、遍历计数数组
        int index = 0;
        int[] sortArray = new int[arr.length];
        for (int i = 0; i < countArr.length; i++) {
//            if (countArr[i] > 0) {
                for (int j = 0; j < countArr[i]; j++) {
                    System.out.print(i + "\t");
                    sortArray[index++] = i;
                }
//            }
        }
        System.out.println();
        return sortArray;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,10,5,6,3,9,8,7,6};
        int[] countSort = countSort(arr);
        System.out.println(Arrays.toString(countSort));
    }
}
