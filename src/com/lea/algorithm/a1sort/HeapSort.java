package com.lea.algorithm.a1sort;

import sun.security.util.Length;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-11-9 19:51
 * 堆排序：
 *  将普通数组 构建成最大堆，再反复删除堆顶，调整后的数组就是 从小到大排序的有序集合
 *
 */
public class HeapSort {

    /**
     * 下沉操作
     * @author: lzc
     * @date: 2020-11-9 19:57
     * @param arr  要排序的数组
     * @param parentIndex  父节点的下标
     * @param length        数组长度
     * @return: void
     */
    public static void downAdjust(int[] arr, int parentIndex, int length) {
        int temp = arr[parentIndex];
        int childIndex = (parentIndex * 2) + 1;
        while (childIndex < length) {
            // 还有右孩子，且右孩子的值比左孩子大
            if (childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 父节点大于等于 子节点 就跳出
            if (temp >= arr[childIndex])
                break;
            // 将父节点下沉
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        arr[parentIndex] = temp;
    }

    public static void heapSort(int[] arr) {
        // 将无序数组下沉到
        for (int i = (arr.length - 2)/2; i >= 0; i--) {
            downAdjust(arr, i, arr.length);
        }
        // 最大堆
        System.out.println(Arrays.toString(arr));
        // 删除堆顶的元素，移到堆尾
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];  // 最后一个
            arr[i] = arr[0];    // 将最大的一个移到堆尾
            arr[0] = temp;
            downAdjust(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,2,6,5,7,8,10,0};
        heapSort(arr);
    }
}
