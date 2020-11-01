package com.lea.algorithm.a1sort;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-1 16:36
 *
 * 冒泡排序 【从小到大排序】
 *  依次比较两个元素的大小，如果前一个元素比后一个元素大 就交换位置，如果前一个元素小于或等于后一个元素 就保持不变。
 *  以此来看，冒泡排序是稳定排序
 */
public class BubbleSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 此种解法是从最后一个非有序区域 开始比较，有点词不达意的感觉
//            for (int j = array.length - i - 1; j > i; j--) {
//              if (array[i] > array[j]) {
//            int temp = array[i];
//            array[i] = array[j];
//            array[j] = temp;
//        }
//            }
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }


    // 优化，如果无序数列已经排序完成时 不需要继续执行
    public void sort2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSort = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSort = false;         // 本轮 有进行比较、交换操作，还没排序完成
                }
            }
            // 本轮没有执行交换操作，数组已经是有序集合了，跳出循环
            if (isSort)
                break;
        }
    }

    // 优化，如果无序数列已经排序完成时 不需要继续执行
    public void sort3(int[] array) {
        int sortOrder = array.length - 1;  // 无序数列的边界。 每次比较到此 就结束
        int lastExchangeIndex = 0;  // 记录最后一次交换的位置
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSort = true;
            for (int j = 0; j < sortOrder; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSort = false;         // 本轮 有进行比较、交换操作，还没排序完成
                    lastExchangeIndex = j;  // 记录j最后操作的下标
                }
            }
            sortOrder = lastExchangeIndex;
            // 本轮没有执行交换操作，数组已经是有序集合了，跳出循环
            if (isSort)
                break;
        }
    }



    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
//        int[] arrays = new int[]{5,1,2,7,8,3};
//        bubbleSort.sort(arrays);
//        System.out.println(Arrays.toString(arrays));

//        arrays = new int[]{5,1,2,7,8,3};
//        bubbleSort.sort2(arrays);
//        System.out.println(Arrays.toString(arrays));
        int[] arrays = new int[]{5,1,2,7,8,9};
        bubbleSort.sort3(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
