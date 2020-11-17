package com.lea.algorithm.interview;

/**
 * @author lzc
 * @create 2020-11-17 21:21
 * 有一个无序整型数组，如何求出该数组排序后的任意两个相邻元素的最大差值？
 * 思路：
 *      这套并不是要求排序，利用线性排序更快
 * 1.暴力解：
 *      先排序，然后遍历相邻的两个元素差值最大的
 * 2.用计数排序，遍历数组连续为0最大的次数
 *
 * 3.用桶排序，记住每个桶的最大值和最小值，遍历获取最大差值  -----》 最优解
 */
public class V6 {

     public static int getMaxSortedDistance(int[] array) {
         // 1.得到数列的最大值和最小值
         int max = array[0];
         int min = array[0];
         for (int i = 1; i < array.length; i++) {
             if (max < array[i])
                 max = array[i];
             if (min > array[i])
                 min = array[i];
         }
         int d = max - min; // 差值
         if (d == 0) // 如果最大值和最小值的差值等于0 那么数组中的数据一样
             return 0;
         //2. 初始化桶
         int bucketNum = array.length;
         Bucket[] buckets = new Bucket[bucketNum];
         for (int i = 0; i < array.length; i++) {
             buckets[i] = new Bucket();
         }
         // 3.遍历原始数组。确定每个桶的最大值 和最小值
         for (int i = 0; i < array.length; i++) {
             // 计算当前值，在哪个桶 （下标）
             int index = (array[i] - min) * (bucketNum - 1) / d;
             if (buckets[index].min == null || buckets[index].min > array[i])
                 buckets[index].min = array[i];
             if (buckets[index].max == null || buckets[index].max < array[i])
                 buckets[index].max = array[i];
         }
         // 4. 遍历桶，找到最大差值
         int leftMax = buckets[0].max;
         int maxDistance = leftMax - buckets[0].min;
         for (int i = 1; i < buckets.length; i++) {
             if (buckets[i].min == null)
                 continue;
             if ((buckets[i].min - leftMax) > maxDistance)
                maxDistance = buckets[i].min - leftMax;

             leftMax = buckets[i].max;
         }
         return maxDistance;
     }

    // 桶
    public static class Bucket {
        Integer min;
        Integer max;
    }

    public static void main(String[] args) {
        int[] array = {1,6,3,4,5,10,9};
        int maxSortedDistance = getMaxSortedDistance(array);
        System.out.println(maxSortedDistance);
    }
}
