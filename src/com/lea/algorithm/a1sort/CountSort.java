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


    /*
    优化都的 计数排序是稳定性的 时间复杂度是O(n+m)
    在下列情况不适合用计数排序进行排序
    1.当数列最大和最小值差距过大时，并不适合计数排序
    2.当数列元素不是整数时，也不适合用计数排序
     */
    // 优化计数排序
    public static int[] countSort2(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        // 1. 获取数组最大 最小的数，控制计数数组的大小
        for (int i = 0; i < arr.length - 1; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        int d = max - min;
        // 计数数组，扩大一位，装最后一个数. 统计对应元素个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - min;
            countArray[index]++;
        }
        // 计数数组变形. 从下标为1的索引开始，等于前面的元素值的和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        // 倒序遍历原始数列，从统计数组找到正确的位置，输出结果数组
        int[] sortedArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = arr[i] - min; // 得到在计数数组中的下标
            int countArrayValue = countArray[index];
            sortedArray[countArrayValue - 1] = arr[i];
            countArray[index]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,10,5,6,3,9,8,7,6};
//        int[] countSort = countSort(arr);
        arr = new int[]{99, 92, 94, 97};
        int[] countSort = countSort2(arr);
        System.out.println(Arrays.toString(countSort));
    }
}
