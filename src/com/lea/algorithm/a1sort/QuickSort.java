package com.lea.algorithm.a1sort;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-2 21:40
 * 快排：在数组中拿一个基准数，采用分治法，定义两个指针，分别指向左、右
 *  从右指针开始 如果大于 基准数，指针左移，如果小于或等于 右指针不动，切换到左指针和基准数做比较
 *  如果左指针的数 比基准数小，则右移一位；如果大，则指针不动，
 *  交换左、右指针的值
 *
 *  直到最有两个指针重合，将基准数 和 交换到重合位置
 */
public class QuickSort {

    // 双边循环法
    public static int partitionDouble(int[] arrays, int startIndex, int endIndex) {
        // 基准数，其他地方会考虑到 最坏的时间复杂度，单独算出一个随机基准数，这里图方便选第一个
        int pivot = arrays[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            // 控制右指针 比较并左移
            while (left < right  && arrays[right] > pivot) {
                right--;
            }
            // 控制左指针 比较并右移
            while (left < right && arrays[left] <= pivot) {
                left++;
            }
            // 交换左右指针的值
            if (left < right) {
                int temp = arrays[left];
                arrays[left] = arrays[right];
                arrays[right] = temp;
            }
        }
        // pivot 和指针重合点交换
        arrays[startIndex] = arrays[left];
        arrays[left] = pivot;
        return left;
    }

    // 双边循环法
    public static void quickSortDouble(int[] arrays, int startIndex, int endIndex) {
        // 结束递归的条件 startIndex 大于或等于 endIndex
        if (startIndex >= endIndex)
            return;
        int pivot = partitionDouble(arrays, startIndex, endIndex);
        quickSortDouble(arrays, startIndex, pivot - 1);
        quickSortDouble(arrays, pivot + 1, endIndex);

    }

    // 单边循环法
    private static int partitionUnilateral(int[] arr, int startIndex, int endIndex) {
        // 基准数
        int pivot = arr[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        // 这一轮 排完了 交换基准数和mark下边的数
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    // 单边循环法
    public static void quickSortUnilateral(int[] arrays, int startIndex, int endIndex) {
        // 结束递归的条件 startIndex 大于或等于 endIndex
        if (startIndex >= endIndex)
            return;
        int pivot = partitionUnilateral(arrays, startIndex, endIndex);
        quickSortUnilateral(arrays, startIndex, pivot - 1);
        quickSortUnilateral(arrays, pivot + 1, endIndex);
    }
    public static void main(String[] args) {
        int[] arrays = new int[] {4,1,6,5,3,2,8};
//        quickSortDouble(arrays, 0, arrays.length - 1);
        quickSortUnilateral(arrays, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays));
    }
}
