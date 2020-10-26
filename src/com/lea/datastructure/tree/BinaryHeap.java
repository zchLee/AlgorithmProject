package com.lea.datastructure.tree;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-10-26 21:25
 * 二叉堆
 *   把二叉堆。完全二叉树存储方式是顺序存储。也就是二叉堆的所有节点都存储在数组中
 *   假设一个父节点下标是 parent
 *   那么 左孩子节点的下标 = parent * 2 + 1;
 *       右孩子节点的下标 = parent * 2 + 2;
 */
public class BinaryHeap {

    /**
     * “上浮”调整
     *
     * @author: lzc
     * @date: 2020-10-26 21:27
     * @param array     带调整的堆
     * @return: void
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex-1)/2;   // 此处我以为会有个bug，其实没有 比如 9/2 和 8/2 的结果都是一样的
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            // 父节点的值和孩子节点的值交换
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;
        }
        // 最后得到的 childIndex下标就是 最小的 节点
        array[childIndex] = temp;
    }

    /**
     * "下沉" 调整
     * @author: lzc
     * @date: 2020-10-26 21:41
     * @param array             待调整的堆
     * @param parentIndex       要“下沉”的父节点
     * @param length            堆的有效大小
     * @return: void
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // 父节点的值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        // 符合条件 父节点就一直下沉
        while (childIndex < length) {
            // 如果有左孩子，且有右孩子，且右孩子比左孩子小，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }
            // 父节点就是最小的就跳过
            if (temp <= array[childIndex]) {
                break;
            }
            // 父节点比孩子节点大，则下沉
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建二叉堆
     * @author: lzc
     * @date: 2020-10-26 22:01
     * @param array
     * @return: void
     */
    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始构建，依次做：下沉：调整
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,5,2,1,8,9,3,6,10};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

    }
}
