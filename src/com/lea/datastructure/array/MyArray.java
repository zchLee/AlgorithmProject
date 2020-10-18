package com.lea.datastructure.array;

/**
 * @author lzc
 * @create 2020-10-14 23:00
 *
 * 数组是顺序结构，在内存中占有一块相连的地址，便于查找，修改或新增的时间复杂度是O(n) 就相对的慢了
 * 特点：
 *  有序，固定大小，元素类型相同
 * 操作：
 *  查找，通过下标就可以直接查找，时间复杂度为O(1)
 */
public class MyArray {

    private int[] array;
    private int size;

    // 构建数组
    public MyArray(int size) {
        if (size < 0 || size > Integer.MAX_VALUE)
            throw new RuntimeException("数组大小超出范围");
        this.array = new int[size];
        this.size = 0;
    }

    // 根据下标查找数据
    public int query(int index) {
        if (0 > index || this.size < index)
            throw new IndexOutOfBoundsException("下标越界");
        return array[index];
    }

    /*
    新增：要分尾部新增、中间插入、越界插入
     */
    // 尾部插入，只需要在数组末尾插入就成
    public void addForEnd(int value) {
        if (this.size >= array.length)
            throw new IndexOutOfBoundsException("下标越界");
        if (this.size >= array.length)
            resize();
        array[size++] = value;
    }

    // 中间插入，要把插入下标以及后面的值，都往后移
    public void addForMiddle(int index, int value) {
        if (0 > index || this.size < index)
            throw new IndexOutOfBoundsException("下标越界");
        // 如果实际元素达到数组容量上限，则对数组进行扩容
        if (this.size >= array.length)
            resize();
        // 移动原有数组, 从最后一个开始移动，往后挪一位
        for (int i = size; i >= index; i--) {
            array[i + 1] = array[i];
        }
        size++;
        array[index] = value;
    }

    // 扩容， 如果目前数组中数据已满，就执行扩容操作
    public void resize() {
//        this.array;
        int[] newArrays = new int[array.length * 2];
        System.arraycopy(this.array, 0, newArrays, 0, array.length);
        array = newArrays;
    }

    // 删除某个元素
    public int delete(int index) {
        if (0 > index || this.size < index)
            throw new IndexOutOfBoundsException("下标越界");
        int deleteElement = array[index];
        // 删除，将index后面的元素往前移动
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        // 数组中个数改变
        size--;
        return deleteElement;
    }

    public static void main(String[] args) {
        MyArray array = new MyArray(3);
        array.addForEnd(2);
        array.addForEnd(1);
        array.addForEnd(3);
        array.addForMiddle(1,23);
        array.addForMiddle(2,13);
        array.out();
        array.delete(2);
        array.out();
    }

    public void out() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t\t");
        }
        System.out.println();
    }

}
