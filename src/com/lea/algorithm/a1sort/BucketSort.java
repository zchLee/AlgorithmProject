package com.lea.algorithm.a1sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedMap;

/**
 * @author lzc
 * @create 2020-11-10 20:38
 * 桶排序
 *      有一组非整数数组，根据这数组，分成若干个桶，每个桶都是有区间范围，再分别对这些桶进行排序，最后输出就是有序数组
 * 具体建多少桶，怎么划分桶的区间，有很多种方式，此处创建桶的数量和数组的长度一致，除最后一个桶只包含最大的数之外，前面
 * 各个桶的区间按照比例来确定
 *      区间跨度 = （最大值-最小值） / (桶的数量 - 1);
 */
public class BucketSort {

    public static double[] bucketSort(double[] array) {
        double max = array[0];
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        double d = max - min;
        System.out.println((max - min) / (array.length - 1));
        // 初始化桶
        int bucketNum = array.length;
        // LinkedList<Double> 表示一个个桶
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        // 遍历原数组，将每个数放进对应的桶中
        for (int i = 0; i < array.length; i++) {
            int num = (int) ((array[i] - min) * (bucketNum - 1) / d);
            bucketList.get(num).add(array[i]);
        }
        // 对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> bucket : bucketList) {
            for (int i = 0; i < bucket.size(); i++) {
                sortedArray[index] = bucket.get(i);
                index++;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{1.3,0.3,2.5,0.22,4.23,5.3};
        double[] doubles = bucketSort(array);
        System.out.println(Arrays.toString(doubles));
    }
}
