package com.lea.datastructure.hashtable;

import java.util.HashMap;

/**
 * @author lzc
 * @create 2020-10-19 22:53
 * 哈希表（散列表）Hash table 存储key-value集合的数据，可以高效的通过key计算到对应的value 时间复杂度在O(1)
 *  hashTable底层是数组，通过哈希函数计算 key应该在哪个下标，然后写入对应的位置
 *  当两个key的哈希函数计算的下标相同时，会发生哈希冲突，有两种解决办法
 *      1.开放寻址法
 *          hash（key）位置有元素后，往后找一位(也可能是其他的操作)，直到找到是空位的再插入 ThreadLocal就是用的开放寻址法
 *      2.链表法
 *          元素位置有元素了，就把新元素插入到就元素的链表上  HashMap就是用的链表法，不过jdk7，jdk8链表插入不同
 *
 *   扩容（以HashTable为例）：
 *      当数组容量大于 数组长度*0.75时，会发生扩容，扩容后，HashTable的元素要重新Hash，让原本拥挤的散列表重新变得稀疏
 */
public class Main {

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(12);
        map.put("key", "value");
    }
}
